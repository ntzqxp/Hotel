package by.epam.hotel.command.impl.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.hotel.command.ActionCommand;
import by.epam.hotel.entity.Nationality;
import by.epam.hotel.entity.Router;
import by.epam.hotel.entity.SessionData;
import by.epam.hotel.exception.CommandException;
import by.epam.hotel.exception.ServiceException;
import by.epam.hotel.service.AdminService;
import by.epam.hotel.util.ConfigurationManager;
import by.epam.hotel.util.MessageManager;
import by.epam.hotel.util.constant.AttributeConstant;
import by.epam.hotel.util.constant.ParameterConstant;
import by.epam.hotel.util.constant.PropertyConstant;
import by.epam.hotel.util.type.RoleType;
import by.epam.hotel.util.type.RouterType;

/**
 * This class is an implementation of a {@link by.epam.hotel.command.ActionCommand ActionCommand} interface 
 * and is used to remove or restore specified nationality.
 * 
 * 
 * @author Evgeniy Moiseyenko
 */
public class ChangeNationalityRemovedCommand implements ActionCommand{
	
	/**
	 * If user's role does not equal to {@link by.epam.hotel.util.type.RoleType#ADMIN ADMIN} 
	 * method  will return user by {@link by.epam.hotel.util.type.RouterType FORWARD} to welcome page.
	 * If specified nationality can not be removed or restored, method will return user by 
	 * {@link by.epam.hotel.util.type.RouterType FORWARD} to previous page.
	 * Otherwise method will restore (if nationality was removed early) or will remove specified nationality
	 * and send admin by {@link by.epam.hotel.util.type.RouterType REDIRECT} to page with 
	 * all nationalities.
	 */
	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = new Router();
		String page = null;
		HttpSession session = request.getSession();
		SessionData sessionData = (SessionData) session.getAttribute(AttributeConstant.SESSION_DATA);
		if (sessionData.getRole() == RoleType.ADMIN) {
			int nationalityIndex = Integer.parseInt(request.getParameter(ParameterConstant.NATIONALITY_INDEX));
			Nationality nationalityToChangeRemoved = sessionData.getNationalityList().get(--nationalityIndex);
			int recordsPerPage = sessionData.getRecordsPerPage();
			int currentPage = sessionData.getCurrentPage();
			try {
				if (AdminService.changeNationalityRemoved(nationalityToChangeRemoved)) {
					List<Nationality> nationalityList = AdminService.getNationalitiesList(currentPage,
							recordsPerPage);
					sessionData.setNationalityList(nationalityList);
					page = ConfigurationManager.getProperty(PropertyConstant.PAGE_ALL_NATIONALITIES);
					router.setType(RouterType.REDIRECT);
				}else {
					request.setAttribute(AttributeConstant.ERROR_CHANGE_NATIONALITY_REMOVED_MESSAGE,
							MessageManager.getProrerty(PropertyConstant.MESSAGE_CHANGE_NATIONALITY_REMOVED_ERROR, sessionData.getLocale()));
					page = ConfigurationManager.getProperty(PropertyConstant.PAGE_ALL_NATIONALITIES);
					router.setType(RouterType.FORWARD);
				}
			} catch (ServiceException e) {
				throw new CommandException(e);
			}
		} else {
			page = ConfigurationManager.getProperty(PropertyConstant.PAGE_WELCOME);
		}
		router.setPage(page);
		return router;
	}

}
