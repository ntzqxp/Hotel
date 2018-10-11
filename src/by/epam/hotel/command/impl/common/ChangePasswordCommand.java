package by.epam.hotel.command.impl.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.hotel.command.ActionCommand;
import by.epam.hotel.controller.Router;
import by.epam.hotel.controller.SessionData;
import by.epam.hotel.exception.CommandException;
import by.epam.hotel.exception.ServiceException;
import by.epam.hotel.service.CommonService;
import by.epam.hotel.util.ConfigurationManager;
import by.epam.hotel.util.MessageManager;
import by.epam.hotel.util.Validator;
import by.epam.hotel.util.constant.AttributeConstant;
import by.epam.hotel.util.constant.ParameterConstant;
import by.epam.hotel.util.constant.PropertyConstant;
import by.epam.hotel.util.type.RoleType;
import by.epam.hotel.util.type.RouterType;

public class ChangePasswordCommand implements ActionCommand {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = new Router();
		String page = null;
		HttpSession session = request.getSession();
		SessionData sessionData = (SessionData) session.getAttribute(AttributeConstant.SESSION_DATA);
		if (sessionData.getRole() == RoleType.CLIENT||sessionData.getRole() == RoleType.ADMIN) {
			String newPassword = request.getParameter(ParameterConstant.NEW_PASSWORD);
			String oldPassword = request.getParameter(ParameterConstant.OLD_PASSWORD);
			String currentLogin = sessionData.getLogin();
			if (Validator.validatePassword(newPassword)) {
				try {
					if (CommonService.checkLogin(currentLogin, oldPassword)) {
						if (CommonService.changePassword(oldPassword, newPassword, currentLogin)) {
							page = ConfigurationManager.getProperty(PropertyConstant.PAGE_SUCCESS_CHANGE_PASSWORD);
							router.setType(RouterType.REDIRECT);
						} else {
							request.setAttribute(AttributeConstant.ERROR_CHANGE_PASSWORD_MESSAGE,
									MessageManager.getProrerty(PropertyConstant.MESSAGE_CHANGE_PASSWORD_ERROR, sessionData.getLocale()));
							page = ConfigurationManager.getProperty(PropertyConstant.PAGE_CHANGE_PASSWORD);
							router.setType(RouterType.FORWARD);
						}
					} else {
						request.setAttribute(AttributeConstant.ERROR_CHAECK_LOGIN_PASSWORD_MESSAGE,
								MessageManager.getProrerty(PropertyConstant.MESSAGE_CHECK_LOGIN_PASSWORD_ERROR, sessionData.getLocale()));
						page = ConfigurationManager.getProperty(PropertyConstant.PAGE_CHANGE_PASSWORD);
						router.setPage(page);
						router.setType(RouterType.FORWARD);
					}
				} catch (ServiceException e) {
					throw new CommandException(e);
				}
			} else {
				request.setAttribute(AttributeConstant.ERROR_PASSWORD_VALIDATE_MESSAGE,
						MessageManager.getProrerty(PropertyConstant.MESSAGE_PASSWORD_VALIDATE_ERROR, sessionData.getLocale()));
				page = ConfigurationManager.getProperty(PropertyConstant.PAGE_CHANGE_PASSWORD);
				router.setType(RouterType.FORWARD);
			}
		} else {
			page = ConfigurationManager.getProperty(PropertyConstant.PAGE_WELCOME);
			router.setType(RouterType.FORWARD);
		}
		router.setPage(page);
		return router;
	}
	
}
