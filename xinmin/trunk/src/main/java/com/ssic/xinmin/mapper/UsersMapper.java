package com.ssic.xinmin.mapper;

import com.ssic.xinmin.pojo.Users;
import com.ssic.xinmin.pojo.UsersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UsersMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xm_users
     *
     * @mbggenerated Mon May 25 15:08:11 CST 2015
     */
    int countByExample(UsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xm_users
     *
     * @mbggenerated Mon May 25 15:08:11 CST 2015
     */
    int deleteByExample(UsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xm_users
     *
     * @mbggenerated Mon May 25 15:08:11 CST 2015
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xm_users
     *
     * @mbggenerated Mon May 25 15:08:11 CST 2015
     */
    int insert(Users record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xm_users
     *
     * @mbggenerated Mon May 25 15:08:11 CST 2015
     */
    int insertSelective(Users record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xm_users
     *
     * @mbggenerated Mon May 25 15:08:11 CST 2015
     */
    List<Users> selectByExample(UsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xm_users
     *
     * @mbggenerated Mon May 25 15:08:11 CST 2015
     */
    Users selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xm_users
     *
     * @mbggenerated Mon May 25 15:08:11 CST 2015
     */
    int updateByExampleSelective(@Param("record") Users record, @Param("example") UsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xm_users
     *
     * @mbggenerated Mon May 25 15:08:11 CST 2015
     */
    int updateByExample(@Param("record") Users record, @Param("example") UsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xm_users
     *
     * @mbggenerated Mon May 25 15:08:11 CST 2015
     */
    int updateByPrimaryKeySelective(Users record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xm_users
     *
     * @mbggenerated Mon May 25 15:08:11 CST 2015
     */
    int updateByPrimaryKey(Users record);
}