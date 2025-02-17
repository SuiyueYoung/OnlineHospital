package com.rectangle.onlinehospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rectangle.onlinehospital.entity.Hospital;
import com.rectangle.onlinehospital.entity.Order;
import com.rectangle.onlinehospital.entity.request.LoginDto;
import com.rectangle.onlinehospital.mapper.OrderMapper;
import com.rectangle.onlinehospital.mapper.UserMapper;
import com.rectangle.onlinehospital.entity.security.UserDetailsDo;
import com.rectangle.onlinehospital.entity.User;
import com.rectangle.onlinehospital.service.HospitalService;
import com.rectangle.onlinehospital.service.UserService;
import com.rectangle.onlinehospital.utils.JwtTokenUtil;
import com.rectangle.onlinehospital.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final OrderMapper orderMapper;
    private final HospitalService hospitalService;

    @Autowired
    public UserServiceImpl(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, PasswordEncoder passwordEncoder, OrderMapper orderMapper, HospitalService hospitalService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
        this.orderMapper = orderMapper;
        this.hospitalService = hospitalService;
    }

    /**
     * @Author Young
     * @Date 6/30/2024 10:10 PM
     * @Description
     * @Param [username, password]
     * @Return com.rectangle.onlinehospital.utils.Result<java.lang.String>
     * @Since version 1.0
     */
    @Override
    public Result<String> userLogin(String username, String password) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            UserDetailsDo userDetailsDo = (UserDetailsDo) authentication.getPrincipal();
            return Result.success(jwtTokenUtil.generateToken(userDetailsDo));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Author Young
     * @Date 6/30/2024 3:01 PM
     * @Description
     * @Param [user]
     * @Return com.rectangle.onlinehospital.utils.Result<java.lang.String>
     * @Since version 1.0
     */
    @Override
    public Result<String> userRegister(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setUserType(1);
            boolean ifRegister = save(user);
            if (ifRegister) {
                return Result.success("token_string");
            } else {
                return Result.error("用户注册失败");
            }
        } catch (AuthenticationException ignore) {
            return Result.error("User register error");
        }
    }

    /**
     * @Author Young
     * @Date 6/30/2024 9:45 PM
     * @Description
     * @Param [user]
     * @Return com.rectangle.onlinehospital.utils.Result<java.lang.String>
     * @Since version 1.0
     */
    @Override
    public Result<String> updateInfo(User user) {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getUserID, user.getUserID())
                .set(User::getSex, user.getSex())
                .set(User::getBirthday, user.getBirthday());
        return update(updateWrapper) ?
                Result.success(user.getUserID() + " update success") :
                Result.error(user.getUserID() + " update error");
    }

    /**
     * @Author Young
     * @Date 6/30/2024 10:09 PM
     * @Description
     * @Param [user]
     * @Return com.rectangle.onlinehospital.utils.Result<java.lang.String>
     * @Since version 1.0
     */
    @Override
    public Result<String> updatePassword(LoginDto updateInfo) {
        try {
            LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(User::getUserID, updateInfo.getUsername())
                    .set(User::getPassword, passwordEncoder.encode(updateInfo.getPassword()));
            return update(updateWrapper) ?
                    Result.success(updateInfo.getUsername() + " update success") :
                    Result.error(updateInfo.getUsername() + " update error");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public Result<String> submitOrder(Order order) {
        try {
            String reserveDate = order.getOrderDate().toString();
            UpdateWrapper<Hospital> updateWrapper = new UpdateWrapper<>();
            updateWrapper.apply("CAST(JSON_EXTRACT(rule, '$.\"" + reserveDate + "\"') AS UNSIGNED) != 0");
            updateWrapper.setSql("rule = JSON_SET(rule, '$.\"" + reserveDate + "\"', CAST(JSON_EXTRACT(rule, '$.\"" + reserveDate + "\"') AS UNSIGNED) - 1)");

            boolean updateResult = hospitalService.update(updateWrapper);

            if (!updateResult) {
                return Result.error("No More Fast Pass for the time of physical examination");
            }

            return orderMapper.insert(order) > 0 ?
                    Result.success(null) :
                    Result.error("Fail to submit order");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
