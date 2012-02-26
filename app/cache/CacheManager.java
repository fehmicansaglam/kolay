package cache;

import java.io.Serializable;
import java.util.concurrent.Callable;

import models.Cumle;
import models.Event;
import models.Role;
import models.User;
import play.Logger;
import play.cache.Cache;
import play.exceptions.UnexpectedException;

public class CacheManager {

    public enum CacheKey {
        USER_COUNT, EVENT_COUNT, ROLE_COUNT, CUMLE_COUNT
    }

    private static final String EXPIRE = "5mn";

    private CacheManager() {

    }

    public static <T extends Serializable> T setIfAbsentAndGet(final CacheKey cacheKey, final Callable<T> callable) {

        final String key = cacheKey.name();
        T value = (T) Cache.get(key);
        if (value == null) {
            Logger.info("CACHE MISS: %s", key);
            try {
                value = callable.call();
            } catch (Exception e) {
                throw new UnexpectedException(e);
            }
            Cache.set(key, value, EXPIRE);
        } else {
            Logger.info("CACHE HIT: %s", key);
        }

        return value;
    }

    public static Long userCount() {

        return setIfAbsentAndGet(CacheKey.USER_COUNT, new Callable<Long>() {

            @Override
            public Long call() throws Exception {

                return User.count();
            }
        });
    }

    public static Long roleCount() {

        return setIfAbsentAndGet(CacheKey.ROLE_COUNT, new Callable<Long>() {

            @Override
            public Long call() throws Exception {

                return Role.count();
            }
        });
    }

    public static Long eventCount() {

        return setIfAbsentAndGet(CacheKey.EVENT_COUNT, new Callable<Long>() {

            @Override
            public Long call() throws Exception {

                return Event.count();
            }
        });
    }

    public static Long cumleCount() {

        return setIfAbsentAndGet(CacheKey.CUMLE_COUNT, new Callable<Long>() {

            @Override
            public Long call() throws Exception {

                return Cumle.count();
            }
        });
    }
}
