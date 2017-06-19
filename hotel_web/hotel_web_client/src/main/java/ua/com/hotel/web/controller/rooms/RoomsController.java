package ua.com.hotel.web.controller.rooms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.hotel.model.entity.room.Room;
import ua.com.hotel.model.pagination.Pageable;
import ua.com.hotel.model.pagination.PaginatedResults;
import ua.com.hotel.service.room.RoomService;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * @since: 05.04.15
 * @author: Андрей
 */
@Controller
@RequestMapping("/rooms")
public class RoomsController {

    private static final String PAGE_FREE_ROOMS = "/client/rooms/freeRooms";

    @Autowired
    private RoomService roomService;

    @RequestMapping(method = RequestMethod.GET)
    public String showFreeRooms(@RequestParam(defaultValue = "1") final int page,
                                @RequestParam(defaultValue = "10") final int perPage,
                                @RequestParam(defaultValue = "price", required = false) final String sort,
                                @RequestParam(defaultValue = EMPTY, required = false) final String filter,
                                final Model model) {

        Pageable pageable = new Pageable(page, perPage, sort, filter);
        PaginatedResults<Room> paginatedFreeRooms = roomService.selectAllFreeRooms(pageable);

        model.addAttribute("paginatedResults", paginatedFreeRooms);
        model.addAttribute("pageable", pageable);

        return PAGE_FREE_ROOMS;
    }
}
