package co.unruly.bowling;

import java.util.function.Supplier;

class Rules {

    static RuleEnforcement checkThat(boolean condition) {
        return otherwise -> {
            if (!condition) throw otherwise.get();
        };
    }

    interface RuleEnforcement {
        void otherwiseThrow(Supplier<? extends RuntimeException> exception);
    }
}
