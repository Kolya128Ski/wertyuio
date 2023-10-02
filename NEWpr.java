import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class TaxiService {
    private String name;
    private int pickupTimeStart;
    private int pickupTimeEnd;
    private int costStart;
    private int costEnd;
    private int travelTimeStart;
    private int travelTimeEnd;
    private String paymentOptions;

    public TaxiService(String data) {
        String[] parts = data.split(", ");
        name = parts[0];
        pickupTimeStart = Integer.parseInt(parts[1].split("-")[0]);
        pickupTimeEnd = Integer.parseInt(parts[1].split("-")[1]);
        costStart = Integer.parseInt(parts[2].split("-")[0]);
        costEnd = Integer.parseInt(parts[2].split("-")[1]);
        travelTimeStart = Integer.parseInt(parts[3].split("-")[0]);
        travelTimeEnd = Integer.parseInt(parts[3].split("-")[1]);
        paymentOptions = parts[4];
    }

    public boolean isSatisfactory(int pickupTime, int cost, int travelTime, String payment) {
        return pickupTime >= pickupTimeStart && pickupTime <= pickupTimeEnd &&
               cost >= costStart && cost <= costEnd &&
               travelTime >= travelTimeStart && travelTime <= travelTimeEnd &&
               (paymentOptions.equals("any") || paymentOptions.contains(payment));
    }

    public String getName() {
        return name;
    }
}

public class TaxiServiceSelector {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            List<TaxiService> services = new ArrayList<>();
            services.add(new TaxiService("Yandex Taxi, 3-8, 330-350, 25-27, any"));
            services.add(new TaxiService("Uber, 7-10, 270-350, 20-26, card"));
            services.add(new TaxiService("Taksovichkoff, 15-17, 100-110, 48-50, cash"));

            int pickupTime = scanner.nextInt();
            int cost = scanner.nextInt();
            int travelTime = scanner.nextInt();
            String payment = scanner.next();

            List<String> suitableServices = new ArrayList<>();
            for (TaxiService service : services) {
                if (service.isSatisfactory(pickupTime, cost, travelTime, payment)) {
                    suitableServices.add(service.getName());
                }
            }

            for (String service : suitableServices) {
                System.out.println(service);
            }
        } catch (Exception e) {
            System.out.println("Ошибка: неверный формат входных данных.");
        } finally {
            scanner.close();
        }
    }
}
