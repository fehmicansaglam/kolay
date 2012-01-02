import models.Cumle;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class BootStrap extends Job {

    @Override
    public void doJob() throws Exception {

        Cumle.deleteAll();
        if (Cumle.count() == 0) {
            new Cumle("Bugün ben eve gittim").save();
            new Cumle("Bugün ben sokağa çıktım").save();
            new Cumle("4Primes her geçen gün büyüyor").save();
            new Cumle("Ben her geçen gün bir şeyler öğreniyorum").save();
        }
    }
}
