package by.epam.hotel.command.impl.client;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.hotel.command.ActionCommand;
import by.epam.hotel.entity.Room;
import by.epam.hotel.entity.Router;
import by.epam.hotel.entity.SessionData;
import by.epam.hotel.exception.CommandException;
import by.epam.hotel.exception.ServiceException;
import by.epam.hotel.service.ClientService;
import by.epam.hotel.util.ConfigurationManager;
import by.epam.hotel.util.constant.AttributeConstant;
import by.epam.hotel.util.constant.ParameterConstant;
import by.epam.hotel.util.constant.PropertyConstant;
import by.epam.hotel.util.type.RoleType;
import by.epam.hotel.util.type.RouterType;

/**
 * This class is an implementation of a
 * {@link by.epam.hotel.command.ActionCommand ActionCommand} interface and is
 * used to generate information to pay for the selected hotel room.
 * 
 * 
 * @author Evgeniy Moiseyenko
 */
public class ChooseRoomCommand implements ActionCommand {

	/**
	 * If user's role does not equal to {@link by.epam.hotel.util.type.RoleType#CLIENT
	 * CLIENT} method will return user by {@link by.epam.hotel.util.type.RouterType
	 * FORWARD} to welcome page. If selected hotel room has already been ordered,
	 * method will return back client by {@link by.epam.hotel.util.type.RouterType FORWARD}
	 * to page with according information. Otherwise method will calculate the order
	 * payment amount for selected hotel room and send client to page with payment
	 * information.
	 */
	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = new Router();
		String page = null;
		HttpSession session = request.getSession();
		SessionData sessionData = (SessionData) session.getAttribute(AttributeConstant.SESSION_DATA);
		if (sessionData.getRole() == RoleType.CLIENT) {
			int chosenRoomNumber = Integer.parseInt(request.getParameter(ParameterConstant.NUMBER));
			List<Room> chachedAvailableRoomList = sessionData.getAvailableRoomList();
			Room chosenRoom = findChosenRoom(chachedAvailableRoomList, chosenRoomNumber);
			LocalDate from = sessionData.getFrom();
			LocalDate to = sessionData.getTo();
			try {
				List<Room> updatedAvailableRoomList = ClientService.findAvailableRoom(chosenRoom.getCapacity(),
						chosenRoom.getClassRoom(), from, to);
				if (updatedAvailableRoomList.contains(chosenRoom)) {
					sessionData.setChosenRoom(chosenRoom);
					long timeStampDiff = to.getLong(ChronoField.EPOCH_DAY) - from.getLong(ChronoField.EPOCH_DAY);
					double toPay = (timeStampDiff != 0 ? timeStampDiff : 1) * chosenRoom.getPrice().doubleValue();
					BigDecimal toPayBigDecimal = new BigDecimal(toPay).setScale(2, RoundingMode.HALF_UP);
					sessionData.setToPay(toPayBigDecimal);
					page = ConfigurationManager.getProperty(PropertyConstant.PAGE_INFO_FOR_PAYMENT);
				} else {
					sessionData.setAvailableRoomList(updatedAvailableRoomList);
					page = ConfigurationManager.getProperty(PropertyConstant.PAGE_ALREADY_ORDERED_ROOM);
				}
			} catch (ServiceException e) {
				throw new CommandException(e);
			}
		} else {
			page = ConfigurationManager.getProperty(PropertyConstant.PAGE_WELCOME);
		}
		router.setType(RouterType.FORWARD);
		router.setPage(page);
		return router;
	}

	private Room findChosenRoom(List<Room> chachedAvailableRoomList, int chosenRoomNumber) {
		Room result = null;
		for (Room chachedAvailableRoom : chachedAvailableRoomList) {
			if (chachedAvailableRoom.getNumber() == chosenRoomNumber) {
				result = chachedAvailableRoom;
			}
		}
		return result;
	}

}
