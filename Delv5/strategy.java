public interface ProductType {
    int planDosage();
}

public class OrganicProduct implements ProductType {
    @Override
    public int planDosage() {
        return 25; // e.g., 25g per square meter
    }
}

public class ChemicalProduct implements ProductType {
    @Override
    public int planDosage() {
        return 10; // e.g., 10g per square meter
    }
}

public abstract class Guider {
    protected ProductType productType;

    public void setStrategy(ProductType strategy) {
        this.productType = strategy;
    }

    public void generateApplicationGuide() {
        System.out.println("Type: " + getFertilizerType());
        System.out.println("Dosage: " + determineDosage() + "g");
        System.out.println("Schedule: " + generateSchedule());
    }

    public abstract String getFertilizerType();

    public int determineDosage() {
        return productType.planDosage();
    }

    public abstract String generateSchedule();
}

public class OrganicFertilizerGuider extends Guider {
    @Override
    public String getFertilizerType() {
        return "Organic Fertilizer";
    }

    @Override
    public String generateSchedule() {
        return "Apply every 4 weeks";
    }
}

public class ChemicalFertilizerGuider extends Guider {
    @Override
    public String getFertilizerType() {
        return "Chemical Fertilizer";
    }

    @Override
    public String generateSchedule() {
        return "Apply every 2 weeks";
    }
}

public class Main {
    public static void main(String[] args) {
        Guider guider = new OrganicFertilizerGuider();
        guider.setStrategy(new OrganicProduct());
        guider.generateApplicationGuide();

        System.out.println("-----");

        guider = new ChemicalFertilizerGuider();
        guider.setStrategy(new ChemicalProduct());
        guider.generateApplicationGuide();
    }
}
