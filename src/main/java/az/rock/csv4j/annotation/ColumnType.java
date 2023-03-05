package az.rock.csv4j.annotation;

import az.rock.csv4j.inspector.type.GUIDTypeReference;
import az.rock.csv4j.inspector.type.TextTypeReference;
import az.rock.csv4j.inspector.type.TypeReference;;

public enum ColumnType {
    TEXT{
        @Override
        public <T> TypeReference<T> typeReference() {
            return new TextTypeReference<>();
        }
    },
    GUID{
        @Override
        public <T> TypeReference<T> typeReference() {
            return new GUIDTypeReference<>();
        }
    },
    ENUM{
        @Override
        public <T> TypeReference<T> typeReference() {
            return null;
        }
    },
    DATE{
        @Override
        public <T> TypeReference<T> typeReference() {
            return null;
        }
    },
    INTEGER{
        @Override
        public <T> TypeReference<T> typeReference() {
            return null;
        }
    },
    LONG{
        @Override
        public <T> TypeReference<T> typeReference() {
            return null;
        }
    },
    BIG_INTEGER{
        @Override
        public <T> TypeReference<T> typeReference() {
            return null;
        }
    },
    BIG_DECIMAL{
        @Override
        public <T> TypeReference<T> typeReference() {
            return null;
        }
    },
    BOOLEAN{
        @Override
        public <T> TypeReference<T> typeReference() {
            return null;
        }
    },
    REFERENCE{
        @Override
        public <T> TypeReference<T> typeReference() {
            return null;
        }
    };

    public abstract <T> TypeReference<T> typeReference();
}
