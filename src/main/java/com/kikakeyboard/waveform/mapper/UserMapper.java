package com.kikakeyboard.waveform.mapper;

import com.kikakeyboard.waveform.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @Author 毛伟
 * @Date 8/4/17  15:52
 */
public interface UserMapper {

    @Select("select real_name from t_user where id = #{id}")
    String getRealNameById(int id);

    @Insert("INSERT INTO t_user(username,password,email,real_name,group_id,description) " +
            "VALUES (#{username},#{password},#{email},#{realName},#{groupId},#{description})")
    boolean save(User user);

    @Update("UPDATE t_voice_package SET username = #{username}, password = #{password}, email = #{email} description = #{description} WHERE id = #{id}")
    boolean update(User user);

}
