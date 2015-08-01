package ua.com.dog.hotel.web.controller.client.rooms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.dog.hotel.model.room.Room;
import ua.com.dog.hotel.service.room.RoomService;
import ua.com.dog.hotel.util.Path;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @since: 05.04.15
 * @author: Андрей
 */
@Controller
@RequestMapping("/rooms")
public class RoomsController {

    @Autowired
    private RoomService roomService;

    @RequestMapping(method = RequestMethod.GET)
    public String showFreeRooms(final Model model) {
        List<Room> freeRooms = roomService.selectAllFreeRooms();
        model.addAttribute("freeRooms", freeRooms);
        return Path.PAGE_ROOMS;
    }
}
