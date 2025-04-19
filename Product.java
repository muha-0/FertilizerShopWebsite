// Abstract Factory Class
public abstract class ProductCreator {

    // Factory Methods
    public abstract LargeProduct createLargeProduct();
    public abstract MediumProduct createMediumProduct();
}

// Concrete Factory Classes
public class LiquidProductCreator extends ProductCreator {

    @Override
    public LargeProduct createLargeProduct() {
        return new LargeLiquidProduct();
    }

    @Override
    public MediumProduct createMediumProduct() {
        return new MediumLiquidProduct();
    }
}

public class SolidProductCreator extends ProductCreator {

    @Override
    public LargeProduct createLargeProduct() {
        return new LargeSolidProduct();
    }

    @Override
    public MediumProduct createMediumProduct() {
        return new MediumSolidProduct();
    }
}

// Interfaces for Products
public interface LargeProduct {
    void doStuff();
}

public interface MediumProduct {
    void doStuff();
}

// Concrete Product Classes
public class LargeLiquidProduct implements LargeProduct {

    @Override
    public void doStuff() {
        System.out.println("Processing large liquid product.");
    }
}

public class MediumLiquidProduct implements MediumProduct {

    @Override
    public void doStuff() {
        System.out.println("Processing medium liquid product.");
    }
}

public class LargeSolidProduct implements LargeProduct {

    @Override
    public void doStuff() {
        System.out.println("Processing large solid product.");
    }
}

public class MediumSolidProduct implements MediumProduct {

    @Override
    public void doStuff() {
        System.out.println("Processing medium solid product.");
    }
}
