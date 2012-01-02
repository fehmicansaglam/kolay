import play.Logger;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class BootStrap extends Job {

    @Override
    public void doJob() throws Exception {

        Configuration.load();
        Logger.info(Configuration.get("kolay.test"));
        Configuration.update("kolay.test", "Updated", false);
    }
}
