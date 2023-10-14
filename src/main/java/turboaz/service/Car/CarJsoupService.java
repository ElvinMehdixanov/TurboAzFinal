package turboaz.service.Car;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import turboaz.dto.Car.CarRequestDto;
import turboaz.entity.CarEntity;
import turboaz.mapper.CarMapper;
import turboaz.repository.CarRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

@Service
public class CarJsoupService {
        private final CarMapper carMapper;
        private final CarRepository carRepository;

        public CarJsoupService(CarMapper carMapper, CarRepository carRepository) {
            this.carMapper = carMapper;
            this.carRepository = carRepository;
        }


        @Scheduled(fixedRate = 30000)
        public CarRequestDto jsoupCar() throws IOException {

        CarRequestDto carRequestDto = new CarRequestDto();

        Document doc = Jsoup.connect("https://turbo.az/autos").get();
        Elements productName = doc.getElementsByClass("products-i");

        for (Element car : productName) {

            Elements carPrice = car.getElementsByClass("product-price");
            Integer price = 0;
            Integer lastPrice = 0;
            String carpriceText = carPrice.text().replace(" ","");
            if (carpriceText.contains("€")){
                price = Integer.valueOf(carpriceText.replace("€",""));
                lastPrice = (int) (price * 1.79);
            }else if (carpriceText.contains("$")){
                price = Integer.valueOf(carpriceText.replace("$",""));
                lastPrice = (int) (price * 1.70);
            } else if (carpriceText.contains("AZN")) {
                price =Integer.valueOf(carpriceText.replace("AZN",""));
                lastPrice =price;
            }


            Elements carDateTime = car.getElementsByClass("products-i__datetime");

            String[] split = carDateTime.text().split(",");
            String region = split[0];
            String date = "";
            String time = "";


            String[] s = split[1].split(" ");
            date = s[1];
            time = s[2];

            if (date.equals("bugün")) {
                LocalDate today = LocalDate.now();
                Date dateToday = java.sql.Date.valueOf(today);
                date = String.valueOf(dateToday);
            } else if (date.equals("dünən")) {
                LocalDate today = LocalDate.now();
                Date dateYesterday = java.sql.Date.valueOf(today.minusDays(1));
                date = String.valueOf(dateYesterday);
            }

            Elements href = car.getElementsByAttribute("href");
            String link = href.attr("abs:href");
            Document document1 = Jsoup.connect(link).get();

            Element make = document1.getElementsByClass("product-properties__i").get(1);
            String makeCar = make.text().replace("Marka", "");

            Element model = document1.getElementsByClass("product-properties__i").get(2);
            String modelCar = model.text().replace("Model", "");

            Element year = document1.getElementsByClass("product-properties__i").get(3);
            String yearCar = year.text().replace("Buraxılış ili", "");

            Element vehicleType = document1.getElementsByClass("product-properties__i").get(4);
            String ban = vehicleType.text().replace("Ban növü", "");

            Element color = document1.getElementsByClass("product-properties__i").get(5);
            String carColor = color.text().replace("Rəng", "");

            Element engine = document1.getElementsByClass("product-properties__i").get(6);
            String carEngine = engine.text().replace("Mühərrik", "");

            String[] split1 = carEngine.split("/");
            String carEngineLast = split1[0];
            String horsePower = split1[1];
            String fuel = split1[2];

            Element odometer = document1.getElementsByClass("product-properties__i").get(7);
            String odometerCar = odometer.text().replace("Yürüş", "");
            String lastOdometer = " ";

            if (odometerCar.contains("km") && !odometerCar.equals("0") ){
                lastOdometer = odometerCar.replace("km","").trim().replace(" ","");
            }else if (odometerCar.equals("0")){
                lastOdometer = odometerCar.replace("km","").trim();
            }

            Element transmission = document1.getElementsByClass("product-properties__i").get(8);
            String transmissionCar = transmission.text().replace("Sürətlər qutusu", "");

            Element gear = document1.getElementsByClass("product-properties__i").get(9);
            String gearCar = gear.text().replace("Ötürücü", "");

            carRequestDto.setMake(makeCar);
            carRequestDto.setModel(modelCar);
            carRequestDto.setPrice(lastPrice);
            carRequestDto.setYear(Integer.valueOf(yearCar));
            carRequestDto.setVehicleType(ban);
            carRequestDto.setColor(carColor);
            carRequestDto.setEngine(carEngineLast);
            carRequestDto.setHorsePower(horsePower);
            carRequestDto.setRegion(region);
            carRequestDto.setFuel(fuel);
            carRequestDto.setOdometer(Integer.valueOf(lastOdometer));
            carRequestDto.setTransmission(transmissionCar);
            carRequestDto.setGear(gearCar);
            //carRequestDto.setDate(date);
            carRequestDto.setTime(time);

            CarEntity carEntity = carMapper.mapRequestDtoToEntity(carRequestDto);

            carRepository.save(carEntity);



        }
        return carRequestDto;

    }

}
