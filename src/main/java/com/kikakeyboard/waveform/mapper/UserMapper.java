package com.kikakeyboard.waveform.mapper;

import com.kikakeyboard.waveform.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author 毛伟
 * @Date 8/4/17  15:52
 */
public interface UserMapper {

    @Select("select real_name from t_user where check_id = #{checkId}")
    List<String> queryMarkersByCheckId(int checkId);

    @Select("select id from t_user where real_name = #{realName}")
    int queryIdByName(String name);

    @Select("select real_name from t_user where id = #{id}")
    String queryNameById(int id);

    @Select("select id from t_user where permission = #{permission}")
    List<Integer> queryIdByPer(int permission);

    @Insert("INSERT INTO t_user(login_name,password,email,permission,real_name,department,introduce) " +
            "VALUES (#{loginName},#{password},#{email},#{permissions},#{userName},#{department},#{introduce})")
    boolean insert(User user);

    @Select("SELECT * FROM `t_user` WHERE login_name=#{userName} AND password=#{password}")
    User getUserByNameAndPassword(User user);
}
