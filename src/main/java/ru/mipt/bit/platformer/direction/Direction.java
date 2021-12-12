package ru.mipt.bit.platformer.direction;

import com.badlogic.gdx.math.GridPoint2;

public enum Direction {
    UP {
        public float getRotation() {
            return 90f;
        }
        public GridPoint2 getChangeVector() {
            return new GridPoint2(0, 1);
        }
    },
    RIGHT {
        public float getRotation() {
            return 0f;
        }
        public GridPoint2 getChangeVector() {
            return new GridPoint2(1, 0);
        }
    },
    DOWN {
        public float getRotation() {
            return -90f;
        }
        public GridPoint2 getChangeVector() {
            return new GridPoint2(0, -1);
        }
    },
    LEFT {
        public float getRotation() {
            return -180f;
        }
        public GridPoint2 getChangeVector() {
            return new GridPoint2(-1, 0);
        }
    };

    public abstract float getRotation();
    public abstract GridPoint2 getChangeVector();
}
