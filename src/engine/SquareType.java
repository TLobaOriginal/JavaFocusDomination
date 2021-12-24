package engine;

public enum SquareType {
    INVALID{
        @Override
        public boolean isValid() {
            return false;
        }

        @Override
        public boolean isInvalid() {
            return true;
        }
    },

    VALID{
        @Override
        public boolean isValid() {
            return true;
        }

        @Override
        public boolean isInvalid() {
            return false;
        }

    };

    public abstract boolean isValid();
    public abstract boolean isInvalid();
}
