package love.broccolai.corn.context;

import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

class DelegatingContextKeyRegistryTest {

    @Test
    void registerSimple() {
        ContextKey<String> key = ContextKey.of("test", "key", String.class);

        DelegatingContextKeyRegistry registry = new DelegatingContextKeyRegistry();
        registry.register(key);

        assertThat(registry).contains(key);
    }

}
