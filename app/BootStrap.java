import models.Cumle;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Bootstrap extends Job {

    @Override
    public void doJob() throws Exception {

        Cumle.deleteAll();
        if (Cumle.count() == 0) {
            new Cumle("Bugün ben eve gittim", Boolean.TRUE).save();
            new Cumle("Bugün ben sokağa çıktım", Boolean.FALSE).save();
            new Cumle("4Primes her geçen gün büyüyor", Boolean.TRUE).save();
            new Cumle("Ben her geçen gün bir şeyler öğreniyorum", Boolean.TRUE).save();
        }
    }
}
