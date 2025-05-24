// Abstract class (Template)
abstract class ApplicationGuideGenerator {

    // Template method
    public final void generateApplicationGuide() {
        getFertilizerType();
        determineDosage();
        generateSchedule();
    }

    protected abstract void getFertilizerType();
    protected abstract void determineDosage();
    protected abstract void generateSchedule();
}

// Concrete class for online orders
class OnlineGuideGenerator extends ApplicationGuideGenerator {

    @Override
    protected void getFertilizerType() {
        System.out.println("Fetching fertilizer type based on user online profile...");
    }

    @Override
    protected void determineDosage() {
        System.out.println("Calculating dosage based on remote soil test results...");
    }

    @Override
    protected void generateSchedule() {
        System.out.println("Generating digital schedule and sending via email...");
    }
}

// Concrete class for in-store orders
class InStoreGuideGenerator extends ApplicationGuideGenerator {

    @Override
    protected void getFertilizerType() {
        System.out.println("Getting fertilizer type based on store inventory consultation...");
    }

    @Override
    protected void determineDosage() {
        System.out.println("Calculating dosage using standard in-store reference chart...");
    }

    @Override
    protected void generateSchedule() {
        System.out.println("Printing schedule and handing it to customer...");
    }
}
