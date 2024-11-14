class RoomBookingSystem {
    public void bookRoom(String guestName) {
        System.out.println("Room booked for " + guestName);
    }

    public void checkAvailability() {
        System.out.println("Checking room availability...");
    }

    public void cancelBooking(String guestName) {
        System.out.println("Room booking cancelled for " + guestName);
    }
}

class RestaurantSystem {
    public void reserveTable(String guestName) {
        System.out.println("Table reserved for " + guestName);
    }

    public void orderFood(String guestName) {
        System.out.println("Food ordered for " + guestName);
    }

    public void callTaxi(String guestName) {
        System.out.println("Taxi ordered for " + guestName);
    }
}

class EventManagementSystem {
    public void bookConferenceRoom(String event) {
        System.out.println("Conference room booked for " + event);
    }

    public void orderEquipment(String event) {
        System.out.println("Equipment ordered for " + event);
    }
}

class CleaningService {
    public void scheduleCleaning(String roomNumber) {
        System.out.println("Cleaning scheduled for room " + roomNumber);
    }

    public void performCleaning(String roomNumber) {
        System.out.println("Cleaning performed for room " + roomNumber);
    }
}


class HotelFacade {
    private RoomBookingSystem roomBookingSystem;
    private RestaurantSystem restaurantSystem;
    private EventManagementSystem eventManagementSystem;
    private CleaningService cleaningService;

    public HotelFacade() {
        this.roomBookingSystem = new RoomBookingSystem();
        this.restaurantSystem = new RestaurantSystem();
        this.eventManagementSystem = new EventManagementSystem();
        this.cleaningService = new CleaningService();
    }

    public void bookRoomWithServices(String guestName) {
        roomBookingSystem.bookRoom(guestName);
        restaurantSystem.orderFood(guestName);
        cleaningService.scheduleCleaning("Room for " + guestName);
        System.out.println("Room booking with restaurant and cleaning services completed for " + guestName);
    }

    public void organizeEvent(String event, String guestName) {
        eventManagementSystem.bookConferenceRoom(event);
        eventManagementSystem.orderEquipment(event);
        roomBookingSystem.bookRoom(guestName);
        System.out.println("Event organized with room and equipment booking for " + guestName);
    }

    public void reserveTableWithTaxi(String guestName) {
        restaurantSystem.reserveTable(guestName);
        restaurantSystem.callTaxi(guestName);
        System.out.println("Table reserved with taxi service for " + guestName);
    }

    public void cancelRoomBooking(String guestName) {
        roomBookingSystem.cancelBooking(guestName);
    }

    public void requestCleaning(String roomNumber) {
        cleaningService.performCleaning(roomNumber);
        System.out.println("On-demand cleaning service performed for room " + roomNumber);
    }
}
class HotelManagementDemo {
    public static void main(String[] args) {
        HotelFacade hotelFacade = new HotelFacade();

        hotelFacade.bookRoomWithServices("John Doe");

        hotelFacade.organizeEvent("Business Meeting", "Alice Smith");

        hotelFacade.reserveTableWithTaxi("Michael Johnson");

        hotelFacade.cancelRoomBooking("John Doe");

        hotelFacade.requestCleaning("Room 101");
    }
}
