package ua.com.hotel.web.controller.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.hotel.model.entity.room.Room;
import ua.com.hotel.service.room.RoomService;

import java.util.List;

/**
 * @author Andrii_Tkachuk
 * @since 8/6/2015
 */
@Controller
@RequestMapping(value = "/admin/rooms")
public class AdminRoomController {

    private static final String PAGE_ROOM_LIST_ADMIN = "/admin/rooms/roomList";

    @Autowired
    private RoomService roomService;

    @RequestMapping(value = "/showList")
    public String showRooms(final Model model){
        List<Room> rooms = roomService.selectAllRooms();
        model.addAttribute("rooms", rooms);
        return PAGE_ROOM_LIST_ADMIN;
    }
}
