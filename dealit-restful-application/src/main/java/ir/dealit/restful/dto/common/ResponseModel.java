package ir.dealit.restful.dto.common;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResponseModel<T> extends RepresentationModel<ResponseModel<T>> {
    private T data;
    private Map<String, Object> metadata;
    private String message;

    public static Builder<?> builder() {
        return new Builder<>();
    }

    public static class Builder<T> {
        private T data;
        private Map<String, Object> metadata = new HashMap<>();
        private String message;

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public Builder<T> metadata(String key, Object value) {
            this.metadata.put(key, value);
            return this;
        }

        public Builder<T> message(String message){
            this.message = message;
            return this;
        }

        public ResponseModel<T> build() {
            ResponseModel<T> responseModel = new ResponseModel<>();
            responseModel.setData(data);
            responseModel.setMetadata(metadata);
            responseModel.setMessage(message);
            return responseModel;
        }

        // Additional methods for predefined messages based on HTTP codes
        public Builder<T> success() {
            this.message = "Success";
            return this;
        }

        public Builder<T> error(String errorMessage) {
            this.message = "Error:" + errorMessage;
            return this;
        }

        // Additional method to handle exceptions
        public Builder<T> error(Exception exception) {
            this.message = "Error:" + exception.getMessage();
            return this;
        }
    }
}
