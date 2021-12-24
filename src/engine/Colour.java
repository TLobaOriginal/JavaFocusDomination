package engine;

public enum Colour {
    RED{
        @Override
        public boolean isRed() { return true; }

        @Override
        public boolean isGreen() {
            return false;
        }

        @Override
        public boolean isUnOccupied() { return false; }

        @Override
        public String toString() {
            return "R";
        }
    },

    GREEN{
        @Override
        public boolean isRed() {
            return false;
        }

        @Override
        public boolean isGreen() {
            return true;
        }

        @Override
        public boolean isUnOccupied() {
            return false;
        }

        @Override
        public String toString() {
            return "G";
        }
    },
    NONE{
        @Override
        public boolean isRed() {
            return false;
        }

        @Override
        public boolean isGreen() {
            return false;
        }

        @Override
        public boolean isUnOccupied() {
            return true;
        }

        @Override
        public String toString() {
            return "U";
        }
    };

    public abstract boolean isRed();
    public abstract boolean isGreen();
    public abstract boolean isUnOccupied();
    public abstract String toString();
}
