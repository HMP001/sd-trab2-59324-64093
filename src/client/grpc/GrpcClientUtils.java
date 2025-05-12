package client.grpc;

import api.java.Result;
import io.grpc.StatusRuntimeException;

import java.util.function.Supplier;

import static api.java.Result.ErrorCode.*;

public class GrpcClientUtils {

    static final long READ_TIMEOUT = 50000;

    static <T> Result<T> wrapRequest(Supplier<Result<T>> f) {
        try {
            return f.get();
        } catch (StatusRuntimeException sre) {
            return Result.error(getErrorCodeFrom(sre));
        }
    }

    static Result.ErrorCode getErrorCodeFrom(StatusRuntimeException status) {
        var code = status.getStatus().getCode();
        return switch (code) {
            case ALREADY_EXISTS -> CONFLICT;
            case PERMISSION_DENIED -> FORBIDDEN;
            case NOT_FOUND -> NOT_FOUND;
            case INVALID_ARGUMENT -> BAD_REQUEST;
            case DEADLINE_EXCEEDED -> TIMEOUT;
            default -> INTERNAL_ERROR;
        };
    }

}
