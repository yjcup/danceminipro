package com.ruoyi.system.controller;

import com.alibaba.fastjson2.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.domain.entity.SysDictType;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.ICourseDetailService;
import com.ruoyi.system.service.ICourseService;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.impl.DanceUserServiceImpl;
import com.ruoyi.system.service.impl.UserCourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/frontend")
public class FrontEndController {



    @Autowired
    private ISysDictDataService dictDataService;
    @Autowired
    private ICourseService courseService;

    // id course_id user_id
    @GetMapping("/purchase")
    public String purchase(){

        return null;
    }

    @GetMapping("/cate")
    public List<SysDictData> cate(){
        SysDictData dictType = new SysDictData();
        dictType.setDictType("course_cate");
        List<SysDictData> sysDictData = dictDataService.selectDictDataList(dictType);
        return sysDictData;
    }


    @GetMapping("/course/{id}")
    public List<Course> Course(@PathVariable String id,HttpServletRequest request){
        Course course = new Course();
        course.setCate(id);
        List<Course> courses = courseService.selectCourseList(course);
        return courses;
    }

    @GetMapping("/courseinfo/{id}")
    public AjaxResult CourseInfo(@PathVariable Long id){
        Course course = courseService.selectCourseById(id);
        String replace = course.getDes().replace("/dev-api", "http://192.168.1.9:8080");
        course.setDes(replace);
        return AjaxResult.success(course);
    }
    /*
    *
    *       用户信息设置
    *id
    *nickname
    *img
    *username
    *password
    *signature
    *create_time
    * 我的课程
    * id
    * user_id
    * course_id
    * create_time
    *
    *
    *
    *
    * */

    //登陆

    @Autowired
    private DanceUserServiceImpl danceUserService;



    private static final String APPID= "wx52155d8944d2a53e";
    private static final String APPSECRET = "0d19386e0a476d122e1e3e7b510d7935";
    @GetMapping("/login")
    public AjaxResult login(String code){
//微信服务器的接口路径
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+APPID+ "&secret="+APPSECRET+"&js_code="+ code+"&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        //进行网络请求,访问微信服务器接口
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        //根据返回值进行后续操作
        WeChatSessionModel weChatSessionModel;

        if(responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
            String sessionData = responseEntity.getBody();
            System.out.println(sessionData);
            JSONObject jsonObject = JSONObject.parseObject(sessionData);
            String sessionKey = jsonObject.getString("session_key");
            String openid = jsonObject.getString("openid");
            if(openid!=null){
                DanceUser danceUser = new DanceUser();
                danceUser.setSignature(openid);
                List<DanceUser> danceUsers = danceUserService.selectDanceUserList(danceUser);
                if(danceUsers.isEmpty()){
                    danceUser.setUsername("weixin"+openid.substring(4).toLowerCase());
                    danceUserService.insertDanceUser(danceUser);
                }
                List<DanceUser> danceUser1 = danceUserService.selectDanceUserList(danceUser);
                return AjaxResult.success(danceUser1.get(0).getId());
            }else{
                return AjaxResult.error("code 错误");
            }
        }
//
//        //
        return AjaxResult.error("get请求错误");
//        DanceUser user = new DanceUser();
//        user.setSignature(sign);
//        List<DanceUser> danceUsers = danceUserService.selectDanceUserList(user);
//        if(danceUsers.isEmpty()){
//            user.setUsername("weixin"+sign.substring(4).toLowerCase());
//            danceUserService.insertDanceUser(user);
//        }
//        List<DanceUser> danceUser1 = danceUserService.selectDanceUserList(user);
//        return danceUser1.get(0).getId();
//        System.out.println(code);
//        return AjaxResult.success();
    }


    @Autowired
    private UserCourseServiceImpl userCourseService;
    @GetMapping("/usercourse/{courseid}/{userid}")
    public String UserCourse(@PathVariable("courseid") Long courseid,@PathVariable("userid") Long userid){
        UserCourse userCourse = new UserCourse();
        userCourse.setCourseId(courseid);
        userCourse.setUserId(userid);
        List<UserCourse> userCourses = userCourseService.selectUserCourseList(userCourse);
        if(userCourses.isEmpty()){
            userCourseService.insertUserCourse(userCourse);
        }
        System.out.println(courseid);
        System.out.println(userid);
        return "insert successfully";
    }


    @GetMapping("/courselist/{id}")
    public  List<Course> CourseList(@PathVariable Long id){
        UserCourse userCourse = new UserCourse();
        userCourse.setUserId(id);
        List<UserCourse> userCourses = userCourseService.selectUserCourseList(userCourse);
        List<Course> res = new ArrayList<>();
        for(UserCourse userCourse1:userCourses){
            Course course = courseService.selectCourseById(userCourse1.getCourseId());
            course.setCreateTime(userCourse1.getCreateTime());
            res.add(course);
        }
        return res;
    }

    @Autowired
    private ICourseDetailService courseDetailService;

    @GetMapping("/coursedetail/{id}")
    public List<CourseDetail> CourseDetail(@PathVariable Long id){
        CourseDetail courseDetail = new CourseDetail();
        courseDetail.setCourseId(id);
        List<CourseDetail> courseDetails = courseDetailService.selectCourseDetailList(courseDetail);
        return courseDetails;
    }

    @GetMapping("/recommendcourse/{userid}")
    public Long RecommentID(@PathVariable Long userid){
        UserCourse userCourse = new UserCourse();
        userCourse.setUserId(userid);
        List<UserCourse> userCourses = userCourseService.selectUserCourseList(userCourse);
        if(userCourses.isEmpty()){
            return 0L;
        }else{
            return userCourses.get(0).getCourseId();
        }
    }


    @Autowired
    private ISysUserService userService;

    @GetMapping("/teacherinfo/{id}")
    public AjaxResult TeacherInfo(@PathVariable Long id) {
        UserCourse userCourse1 = new UserCourse();
        userCourse1.setCourseId(id);
        List<UserCourse> userCourses = userCourseService.selectUserCourseList(userCourse1);
        SysUser sysUser = userService.selectUserById(userCourses.get(0).getUserId());
        System.out.println(sysUser);
        return AjaxResult.success(sysUser);
    }


    @GetMapping("/recommend/{page}")
    public PageInfo<Course> Recomment(@PathVariable Integer page ){
        PageHelper.startPage(page,4);
        List<Course> courses = courseService.selectCourseList(new Course());
        PageInfo<Course> pageInfo = new PageInfo<Course>(courses) ;
        PageHelper.clearPage();


        return pageInfo;
    }



}
