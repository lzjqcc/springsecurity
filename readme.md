## spring security学习

spring security是由一堆过滤器和aop来控制用户访问和认证

#### spring security XML文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<b:beans xmlns="http://www.springframework.org/schema/security"
         xmlns:b="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"

         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
                        http://www.springframework.org/schema/security http://www.springframework.org/schema/security/spring-security-4.0.xsd">
    <b:import resource="application.xml"/>
    <http pattern="/login.jsp*" security="none"></http>
    <http pattern="/**/*.jpg" security="none"/>
    <http pattern="/**/*.png" security="none"/>
    <http pattern="/**/*.gif" security="none"/>
    <http pattern="/**/*.ico" security="none"/>
    <http pattern="/**/*.css" security="none"/>
    <http pattern="/**/*.js" security="none"/>
    <http pattern="/error.jsp*" security="none"/>
    <http auto-config="true" >
        <!--login-page的路径一定得在项目中找到，路径要符合-->
        <!---->
        <form-login username-parameter="username" password-parameter="password" login-page="/login.jsp"  authentication-failure-url="/login.jsp?error=true"></form-login>
        <intercept-url pattern="/user/**" access="hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')"/>
        <intercept-url pattern="/admin/**" access="hasRole('ROLE_ADMIN')"/>
        <csrf disabled="true"/>
        <!--配置403显示的也面-->
        <access-denied-handler error-page="/error.jsp"/>
    </http>
    <!--uses-by-username-query通过用户名查询用户信息-->
    <!--authorities-by-username-query通过用户名查询权限信息-->
    <!--group-authorities-by-username-query通过用户名查询用户组权限-->
    <authentication-manager>
        <authentication-provider >
          <!--  <user-service>
                &lt;!&ndash;<user name="li" password="123" authorities="ROLE_USER"></user>&ndash;&gt;
            </user-service>-->
            <!--要是查询两个报错 3>2 查询大于三个密码用户名错误-->
            <jdbc-user-service data-source-ref="dataSource"
                users-by-username-query="SELECT  username,password,status FROM springsecurity.user where username=?;"
                authorities-by-username-query="SELECT u.username,r.name FROM (springsecurity.user u inner join springsecurity.role_user ru on u.id=ru.user_id) inner join springsecurity.role r on r.id=ru.role_id where u.username=?"
            />
        </authentication-provider>
    </authentication-manager>
    <b:bean id="messageSources" class="org.springframework.context.support.ResourceBundleMessageSource">
        <b:property name="basename" value="myerrormessage">
        </b:property>
    </b:bean>
</b:beans>
```

#### 自定义登录表单

这里注意**method为post**

```jsp
  
<form action="<%=application.getContextPath()%>/login" method="post">
        <input type="text" name="username"></br>
        <input type="password" name="password"><br>
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
        <input type="submit" value="login">
    </form>
```

#### spring mvc控制器

```
@Controller
public class HelloWordController {
    @RequestMapping("/loginx")
    public String index(){
        //AbstractUserDetailsAuthenticationProvider
        System.out.print("springmvc");
        return "login";
    }
    @RequestMapping("/admin")
    public String admin(Model model){
        model.addAttribute("username",getPrincipal());
        return "admin/admin";
    }
    @RequestMapping("/user")
    public String user(Model model){
        model.addAttribute("username",getPrincipal());
        return "user/index";
    }
    private String getPrincipal(){
        String userName=null;
        Object object=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (object instanceof UserDetails){
            userName=((UserDetails)object).getUsername();
        }else {
            userName=object.toString();
        }
        return userName;
    }
}
```