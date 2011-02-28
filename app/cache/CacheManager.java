package cache;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import models.Cumle;
import models.Event;
import models.Role;
import models.User;
import play.Logger;
import play.cache.Cache;
import play.db.jpa.Model;
import play.exceptions.UnexpectedException;

public class CacheManager {

    public enum CacheKey {
        CUMLELER(new Callable<ArrayList<Cumle>>() {

            @Override
            public ArrayList<Cumle> call() throws Exception {

                return (ArrayList<Cumle>) Cumle.<Cumle> findAll();
            }
        }), USER_COUNT(User.class, "count"), EVENT_COUNT(Event.class, "count"),
        // Callable parametre örneği
        ROLE_COUNT(Role.class, "count"),
        CUMLE_COUNT(Cumle.class, "count"),
        CUMLE_ENABLED_COUNT(
            Cumle.class, "count",
            new Class<?>[] { String.class, Object[].class }, "enabled=?",
            new Object[] { Boolean.TRUE });

        private Method method;

        private Object[] params;

        private Callable<? extends Serializable> callable;

        private CacheKey(Class<? extends Model> model, String method,
                Class<?>[] parameterTypes, Object... params) {

            try {
                this.method = model.getDeclaredMethod(method, parameterTypes);
                this.params = params;
            } catch (SecurityException e) {
                throw new UnexpectedException(e);
            } catch (NoSuchMethodException e) {
                throw new UnexpectedException(e);
            }
        }

        private CacheKey(Class<? extends Model> model, String method) {

            this(model, method, null);
        }

        private CacheKey(Callable<? extends Serializable> callable) {

            this.callable = callable;
        }

        public <T> T invoke() throws Exception {

            if (this.callable == null)
                return (T) this.method.invoke(null, params);
            else
                return (T) callable.call();
        }
    }

    private static final String EXPIRE = "5mn";

    private CacheManager() {

    }

    public static <T extends Serializable> T get(final CacheKey cacheKey) {

        final String key = cacheKey.name();
        T value = (T) Cache.get(key);
        if (value == null) {
            Logger.info("CACHE MISS: %s", key);
            try {
                value = cacheKey.invoke();
            } catch (Exception e) {
                throw new UnexpectedException(e);
            }
            Cache.set(key, value, EXPIRE);
        } else {
            Logger.info("CACHE HIT: %s", key);
        }

        return value;
    }

    public static <T extends Serializable> T reset(final CacheKey cacheKey) {

        final String key = cacheKey.name();
        Logger.info("CACHE RESET: %s", key);
        try {
            T value = cacheKey.invoke();
            Cache.set(key, value, EXPIRE);
            return value;
        } catch (Exception e) {
            throw new UnexpectedException(e);
        }
    }

}
