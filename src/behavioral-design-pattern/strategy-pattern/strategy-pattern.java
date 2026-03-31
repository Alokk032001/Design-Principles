interface MatchingStrategy{
    void match(String riderLocation);
}

class NearestDriverStrategy implements MatchingStrategy {
    @Override
    public void match(String riderLocation) {
        System.out.println("Matching rider at " + riderLocation + " with the nearest driver.");
    }
}

class AirportQueueStrategy implements MatchingStrategy {
    @Override
    public void match(String riderLocation) {
        System.out.println("Matching rider at " + riderLocation + " with the driver in the airport queue.");
    }
}

class SurgePricingStrategy implements MatchingStrategy {
    @Override
    public void match(String riderLocation) {
        System.out.println("Matching rider at " + riderLocation + " with a driver during surge pricing.");
    }
}

class RideService {
    private MatchingStrategy strategy;

    public RideService(MatchingStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(MatchingStrategy strategy) {
        this.strategy = strategy;
    }

    public void findDriver(String riderLocation) {
        strategy.match(riderLocation);
    }
}

class Main {
    public static void main(String[] args) {
        RideService rideService = new RideService(new NearestDriverStrategy());
        rideService.findDriver("Downtown");

        rideService.setStrategy(new AirportQueueStrategy());
        rideService.findDriver("Airport");

        rideService.setStrategy(new SurgePricingStrategy());
        rideService.findDriver("Suburbs");
    }
}

