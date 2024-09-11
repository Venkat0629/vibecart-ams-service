package com.nisum.vibe.cart.account.config;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;
@Component
public class TokenCache {

    private final Cache<String, Long> tokenCache;

    public TokenCache() {

        this.tokenCache = CacheBuilder.newBuilder()
                .expireAfterWrite(6, TimeUnit.MINUTES) // Tokens expire after 6 minutes
                .build();
    }

    // Store the token and associated userId
    public void put(String token, Long userId) {
        tokenCache.put(token, userId);
    }

    // Retrieve the userId associated with the token
    public Long get(String token) {
        return tokenCache.getIfPresent(token);
    }

    // Remove the token
    public void remove(String token) {
        tokenCache.invalidate(token);
    }
}

