package ua.com.hotel.web.validator.client;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.util.Date;

/**
 * @author Andrii_Tkachuk
 * @since 4/8/2015
 */
public class ReservationValidator {

    private static final Logger LOG = Logger.getLogger(ReservationValidator.class);

    public String validate(Date dateCheckIn, Date dateCheckOut) {
        Date currentDate = new Date(System.currentTimeMillis());
        if (dateCheckIn == null || dateCheckOut == null) {
            LOG.trace("Incorrect input: Empty fields for date input found, filling required");
            return "error.reservation.empty.fields";
        } else if (dateCheckIn.after(dateCheckOut)) {
            LOG.trace("Incorrect input: Check-in must be previous check-out");
            return "error.reservation.chInPrevChOut";
        } else if (dateCheckIn.equals(dateCheckOut)) {
            LOG.trace("Incorrect input: Arrival and departure date can not be on the same day.");
            return "error.reservation.arriv.eq.depart";
        } /*else if (!currentDate.equals(dateCheckIn) || currentDate.after(dateCheckIn)) {
            LOG.trace("Arrival date shall not be earlier than today.");
            return "error.reservation.arriv.lesEq.curr";
        }*/

        return StringUtils.EMPTY;
    }
}
