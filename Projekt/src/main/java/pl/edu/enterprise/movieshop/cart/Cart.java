package pl.edu.enterprise.movieshop.cart;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart implements Serializable {

    private final Map<Long, Integer> items = new LinkedHashMap<Long, Integer>();

    public void add(Long movieId) {
        Integer quantity = items.get(movieId);
        items.put(movieId, quantity == null ? 1 : quantity + 1);
    }

    public void remove(Long movieId) {
        items.remove(movieId);
    }

    public void clear() {
        items.clear();
    }

    public Map<Long, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public int getItemCount() {
        int count = 0;
        for (Integer quantity : items.values()) {
            count += quantity;
        }
        return count;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
