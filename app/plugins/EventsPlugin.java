package plugins;

import models.Cumle;
import play.PlayPlugin;
import cache.CacheManager;
import cache.CacheManager.CacheKey;

public class EventsPlugin extends PlayPlugin {

    @Override
    public void onEvent(String message, Object context) {

        if ("JPASupport.objectPersisted".equals(message)) {
            if (context instanceof Cumle) {
                CacheManager.reset(CacheKey.CUMLELER);
            }
        } else if ("JPASupport.objectUpdated".equals(message)) {
            if (context instanceof Cumle) {
                CacheManager.reset(CacheKey.CUMLELER);
            }
        } else if ("JPASupport.objectDeleted".equals(message)) {
            if (context instanceof Cumle) {
                CacheManager.reset(CacheKey.CUMLELER);
            }
        }
    }
}
