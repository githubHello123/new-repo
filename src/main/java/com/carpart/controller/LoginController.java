package com.carpart.controller;

import com.carpart.entity.User;
import com.carpart.manager.ClientManager;
import com.carpart.model.AjaxJson;
import com.carpart.model.Client;
import com.carpart.service.UserService;
import com.gx.util.ContextHolderUtils;
import com.gx.util.IpUtil;
import com.gx.util.ResourceUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;


@Scope("prototype")
@Controller
@RequestMapping("loginController.do")
public class LoginController{
	private Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(params = "goPwdInit")
	public String goPwdInit() {
		return "login/pwd_init";
	}

	/**
	 * admin账户密码初始化
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "pwdInit")
	public ModelAndView pwdInit(HttpServletRequest request) {
		ModelAndView modelAndView = null;
		String userName = "admin";
		String newPwd = "123456";
		userService.pwdInit(userName, newPwd);
		modelAndView = new ModelAndView(new RedirectView("loginController.do?login"));
		return modelAndView;
	}

	/**
	 * 检查用户名称
	 * 
	 * @param user
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "checkuser")
	@ResponseBody
	public AjaxJson checkuser(User user, HttpServletRequest req) {
		HttpSession session = ContextHolderUtils.getSession();
		AjaxJson j = new AjaxJson();
        String randCode = req.getParameter("randCode");
		String message = "";
        if (StringUtils.isEmpty(randCode)) {
            j.setMsg("请输入验证码");
            j.setSuccess(false);
        } else if (!randCode.equalsIgnoreCase(String.valueOf(session.getAttribute("randCode")))) {
            // todo "randCode"和验证码servlet中该变量一样，通过统一的系统常量配置比较好，暂时不知道系统常量放在什么地方合适
            j.setMsg("验证码错误！");
            j.setSuccess(false);
        } else {
            int users = userService.findAll().size();
            
            if (users == 0) {
                j.setMsg("a");
                j.setSuccess(false);
            } else {
            	log.info("....name..."+user.getUserName()+"...password..."+user.getPassword());
                User u = userService.checkUserExits(user);
                if(u == null) {
                    j.setMsg("用户名或密码错误!");
                    j.setSuccess(false);
                    return j;
                }
            	User u2 = userService.findOne(u.getId());
                if (u != null&&u2.getStatus()!=0) {
					message = "用户: " + user.getUserName()  + "登录成功";
					Client client = new Client();
					client.setIp(IpUtil.getIpAddr(req));
					client.setLogindatetime(new Date());
					client.setUser(u);
					ClientManager.getInstance().addClinet(session.getId(), client);
					// 添加登陆日志
					//systemService.addLog(message, Globals.Log_Type_LOGIN, Globals.Log_Leavel_INFO);
                } else {
                    j.setMsg("用户名或密码错误!");
                    j.setSuccess(false);
                }
            }
        }
		return j;
	}

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "login")
	public String login(ModelMap modelMap, HttpServletRequest request) {
		User user = ResourceUtil.getSessionUserName();
		if (user != null) {
            modelMap.put("userName", user.getUserName());
			request.getSession().setAttribute("CKFinder_UserRole", "admin");
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				if (cookie == null || StringUtils.isEmpty(cookie.getName())) {
					continue;
				}
			}
			return "main/shortcut_main";
		} else {
			return "login/login";
		}

	}

	/**
	 * 退出系统
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "logout")
	public ModelAndView logout(HttpServletRequest request) {
		HttpSession session = ContextHolderUtils.getSession();
		User user = ResourceUtil.getSessionUserName();
		//systemService.addLog("用户" + user.getUserName() + "已退出", Globals.Log_Type_EXIT, Globals.Log_Leavel_INFO);
		ClientManager.getInstance().removeClinet(session.getId());
		ModelAndView modelAndView = new ModelAndView(new RedirectView("loginController.do?login"));
		return modelAndView;
	}


}
