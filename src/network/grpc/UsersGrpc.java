package network.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.62.2)",
    comments = "Source: src/api/grpc/Users.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class UsersGrpc {

  private UsersGrpc() {}

  public static final java.lang.String SERVICE_NAME = "Users";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<network.grpc.UsersProtoBuf.CreateUserArgs,
      network.grpc.UsersProtoBuf.CreateUserResult> getCreateUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "createUser",
      requestType = network.grpc.UsersProtoBuf.CreateUserArgs.class,
      responseType = network.grpc.UsersProtoBuf.CreateUserResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<network.grpc.UsersProtoBuf.CreateUserArgs,
      network.grpc.UsersProtoBuf.CreateUserResult> getCreateUserMethod() {
    io.grpc.MethodDescriptor<network.grpc.UsersProtoBuf.CreateUserArgs, network.grpc.UsersProtoBuf.CreateUserResult> getCreateUserMethod;
    if ((getCreateUserMethod = UsersGrpc.getCreateUserMethod) == null) {
      synchronized (UsersGrpc.class) {
        if ((getCreateUserMethod = UsersGrpc.getCreateUserMethod) == null) {
          UsersGrpc.getCreateUserMethod = getCreateUserMethod =
              io.grpc.MethodDescriptor.<network.grpc.UsersProtoBuf.CreateUserArgs, network.grpc.UsersProtoBuf.CreateUserResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "createUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.UsersProtoBuf.CreateUserArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.UsersProtoBuf.CreateUserResult.getDefaultInstance()))
              .setSchemaDescriptor(new UsersMethodDescriptorSupplier("createUser"))
              .build();
        }
      }
    }
    return getCreateUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<network.grpc.UsersProtoBuf.GetUserArgs,
      network.grpc.UsersProtoBuf.GetUserResult> getGetUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getUser",
      requestType = network.grpc.UsersProtoBuf.GetUserArgs.class,
      responseType = network.grpc.UsersProtoBuf.GetUserResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<network.grpc.UsersProtoBuf.GetUserArgs,
      network.grpc.UsersProtoBuf.GetUserResult> getGetUserMethod() {
    io.grpc.MethodDescriptor<network.grpc.UsersProtoBuf.GetUserArgs, network.grpc.UsersProtoBuf.GetUserResult> getGetUserMethod;
    if ((getGetUserMethod = UsersGrpc.getGetUserMethod) == null) {
      synchronized (UsersGrpc.class) {
        if ((getGetUserMethod = UsersGrpc.getGetUserMethod) == null) {
          UsersGrpc.getGetUserMethod = getGetUserMethod =
              io.grpc.MethodDescriptor.<network.grpc.UsersProtoBuf.GetUserArgs, network.grpc.UsersProtoBuf.GetUserResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.UsersProtoBuf.GetUserArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.UsersProtoBuf.GetUserResult.getDefaultInstance()))
              .setSchemaDescriptor(new UsersMethodDescriptorSupplier("getUser"))
              .build();
        }
      }
    }
    return getGetUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<network.grpc.UsersProtoBuf.UpdateUserArgs,
      network.grpc.UsersProtoBuf.UpdateUserResult> getUpdateUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "updateUser",
      requestType = network.grpc.UsersProtoBuf.UpdateUserArgs.class,
      responseType = network.grpc.UsersProtoBuf.UpdateUserResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<network.grpc.UsersProtoBuf.UpdateUserArgs,
      network.grpc.UsersProtoBuf.UpdateUserResult> getUpdateUserMethod() {
    io.grpc.MethodDescriptor<network.grpc.UsersProtoBuf.UpdateUserArgs, network.grpc.UsersProtoBuf.UpdateUserResult> getUpdateUserMethod;
    if ((getUpdateUserMethod = UsersGrpc.getUpdateUserMethod) == null) {
      synchronized (UsersGrpc.class) {
        if ((getUpdateUserMethod = UsersGrpc.getUpdateUserMethod) == null) {
          UsersGrpc.getUpdateUserMethod = getUpdateUserMethod =
              io.grpc.MethodDescriptor.<network.grpc.UsersProtoBuf.UpdateUserArgs, network.grpc.UsersProtoBuf.UpdateUserResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "updateUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.UsersProtoBuf.UpdateUserArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.UsersProtoBuf.UpdateUserResult.getDefaultInstance()))
              .setSchemaDescriptor(new UsersMethodDescriptorSupplier("updateUser"))
              .build();
        }
      }
    }
    return getUpdateUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<network.grpc.UsersProtoBuf.DeleteUserArgs,
      network.grpc.UsersProtoBuf.DeleteUserResult> getDeleteUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "deleteUser",
      requestType = network.grpc.UsersProtoBuf.DeleteUserArgs.class,
      responseType = network.grpc.UsersProtoBuf.DeleteUserResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<network.grpc.UsersProtoBuf.DeleteUserArgs,
      network.grpc.UsersProtoBuf.DeleteUserResult> getDeleteUserMethod() {
    io.grpc.MethodDescriptor<network.grpc.UsersProtoBuf.DeleteUserArgs, network.grpc.UsersProtoBuf.DeleteUserResult> getDeleteUserMethod;
    if ((getDeleteUserMethod = UsersGrpc.getDeleteUserMethod) == null) {
      synchronized (UsersGrpc.class) {
        if ((getDeleteUserMethod = UsersGrpc.getDeleteUserMethod) == null) {
          UsersGrpc.getDeleteUserMethod = getDeleteUserMethod =
              io.grpc.MethodDescriptor.<network.grpc.UsersProtoBuf.DeleteUserArgs, network.grpc.UsersProtoBuf.DeleteUserResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "deleteUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.UsersProtoBuf.DeleteUserArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.UsersProtoBuf.DeleteUserResult.getDefaultInstance()))
              .setSchemaDescriptor(new UsersMethodDescriptorSupplier("deleteUser"))
              .build();
        }
      }
    }
    return getDeleteUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<network.grpc.UsersProtoBuf.SearchUserArgs,
      network.grpc.UsersProtoBuf.GrpcUser> getSearchUsersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "searchUsers",
      requestType = network.grpc.UsersProtoBuf.SearchUserArgs.class,
      responseType = network.grpc.UsersProtoBuf.GrpcUser.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<network.grpc.UsersProtoBuf.SearchUserArgs,
      network.grpc.UsersProtoBuf.GrpcUser> getSearchUsersMethod() {
    io.grpc.MethodDescriptor<network.grpc.UsersProtoBuf.SearchUserArgs, network.grpc.UsersProtoBuf.GrpcUser> getSearchUsersMethod;
    if ((getSearchUsersMethod = UsersGrpc.getSearchUsersMethod) == null) {
      synchronized (UsersGrpc.class) {
        if ((getSearchUsersMethod = UsersGrpc.getSearchUsersMethod) == null) {
          UsersGrpc.getSearchUsersMethod = getSearchUsersMethod =
              io.grpc.MethodDescriptor.<network.grpc.UsersProtoBuf.SearchUserArgs, network.grpc.UsersProtoBuf.GrpcUser>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "searchUsers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.UsersProtoBuf.SearchUserArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.UsersProtoBuf.GrpcUser.getDefaultInstance()))
              .setSchemaDescriptor(new UsersMethodDescriptorSupplier("searchUsers"))
              .build();
        }
      }
    }
    return getSearchUsersMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UsersStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UsersStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UsersStub>() {
        @java.lang.Override
        public UsersStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UsersStub(channel, callOptions);
        }
      };
    return UsersStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UsersBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UsersBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UsersBlockingStub>() {
        @java.lang.Override
        public UsersBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UsersBlockingStub(channel, callOptions);
        }
      };
    return UsersBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UsersFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UsersFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UsersFutureStub>() {
        @java.lang.Override
        public UsersFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UsersFutureStub(channel, callOptions);
        }
      };
    return UsersFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void createUser(network.grpc.UsersProtoBuf.CreateUserArgs request,
        io.grpc.stub.StreamObserver<network.grpc.UsersProtoBuf.CreateUserResult> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateUserMethod(), responseObserver);
    }

    /**
     */
    default void getUser(network.grpc.UsersProtoBuf.GetUserArgs request,
        io.grpc.stub.StreamObserver<network.grpc.UsersProtoBuf.GetUserResult> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetUserMethod(), responseObserver);
    }

    /**
     */
    default void updateUser(network.grpc.UsersProtoBuf.UpdateUserArgs request,
        io.grpc.stub.StreamObserver<network.grpc.UsersProtoBuf.UpdateUserResult> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateUserMethod(), responseObserver);
    }

    /**
     */
    default void deleteUser(network.grpc.UsersProtoBuf.DeleteUserArgs request,
        io.grpc.stub.StreamObserver<network.grpc.UsersProtoBuf.DeleteUserResult> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteUserMethod(), responseObserver);
    }

    /**
     */
    default void searchUsers(network.grpc.UsersProtoBuf.SearchUserArgs request,
        io.grpc.stub.StreamObserver<network.grpc.UsersProtoBuf.GrpcUser> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSearchUsersMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Users.
   */
  public static abstract class UsersImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return UsersGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Users.
   */
  public static final class UsersStub
      extends io.grpc.stub.AbstractAsyncStub<UsersStub> {
    private UsersStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UsersStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UsersStub(channel, callOptions);
    }

    /**
     */
    public void createUser(network.grpc.UsersProtoBuf.CreateUserArgs request,
        io.grpc.stub.StreamObserver<network.grpc.UsersProtoBuf.CreateUserResult> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getUser(network.grpc.UsersProtoBuf.GetUserArgs request,
        io.grpc.stub.StreamObserver<network.grpc.UsersProtoBuf.GetUserResult> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateUser(network.grpc.UsersProtoBuf.UpdateUserArgs request,
        io.grpc.stub.StreamObserver<network.grpc.UsersProtoBuf.UpdateUserResult> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteUser(network.grpc.UsersProtoBuf.DeleteUserArgs request,
        io.grpc.stub.StreamObserver<network.grpc.UsersProtoBuf.DeleteUserResult> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void searchUsers(network.grpc.UsersProtoBuf.SearchUserArgs request,
        io.grpc.stub.StreamObserver<network.grpc.UsersProtoBuf.GrpcUser> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getSearchUsersMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Users.
   */
  public static final class UsersBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<UsersBlockingStub> {
    private UsersBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UsersBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UsersBlockingStub(channel, callOptions);
    }

    /**
     */
    public network.grpc.UsersProtoBuf.CreateUserResult createUser(network.grpc.UsersProtoBuf.CreateUserArgs request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public network.grpc.UsersProtoBuf.GetUserResult getUser(network.grpc.UsersProtoBuf.GetUserArgs request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public network.grpc.UsersProtoBuf.UpdateUserResult updateUser(network.grpc.UsersProtoBuf.UpdateUserArgs request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public network.grpc.UsersProtoBuf.DeleteUserResult deleteUser(network.grpc.UsersProtoBuf.DeleteUserArgs request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<network.grpc.UsersProtoBuf.GrpcUser> searchUsers(
        network.grpc.UsersProtoBuf.SearchUserArgs request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getSearchUsersMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Users.
   */
  public static final class UsersFutureStub
      extends io.grpc.stub.AbstractFutureStub<UsersFutureStub> {
    private UsersFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UsersFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UsersFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<network.grpc.UsersProtoBuf.CreateUserResult> createUser(
        network.grpc.UsersProtoBuf.CreateUserArgs request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateUserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<network.grpc.UsersProtoBuf.GetUserResult> getUser(
        network.grpc.UsersProtoBuf.GetUserArgs request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetUserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<network.grpc.UsersProtoBuf.UpdateUserResult> updateUser(
        network.grpc.UsersProtoBuf.UpdateUserArgs request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateUserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<network.grpc.UsersProtoBuf.DeleteUserResult> deleteUser(
        network.grpc.UsersProtoBuf.DeleteUserArgs request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteUserMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_USER = 0;
  private static final int METHODID_GET_USER = 1;
  private static final int METHODID_UPDATE_USER = 2;
  private static final int METHODID_DELETE_USER = 3;
  private static final int METHODID_SEARCH_USERS = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_USER:
          serviceImpl.createUser((network.grpc.UsersProtoBuf.CreateUserArgs) request,
              (io.grpc.stub.StreamObserver<network.grpc.UsersProtoBuf.CreateUserResult>) responseObserver);
          break;
        case METHODID_GET_USER:
          serviceImpl.getUser((network.grpc.UsersProtoBuf.GetUserArgs) request,
              (io.grpc.stub.StreamObserver<network.grpc.UsersProtoBuf.GetUserResult>) responseObserver);
          break;
        case METHODID_UPDATE_USER:
          serviceImpl.updateUser((network.grpc.UsersProtoBuf.UpdateUserArgs) request,
              (io.grpc.stub.StreamObserver<network.grpc.UsersProtoBuf.UpdateUserResult>) responseObserver);
          break;
        case METHODID_DELETE_USER:
          serviceImpl.deleteUser((network.grpc.UsersProtoBuf.DeleteUserArgs) request,
              (io.grpc.stub.StreamObserver<network.grpc.UsersProtoBuf.DeleteUserResult>) responseObserver);
          break;
        case METHODID_SEARCH_USERS:
          serviceImpl.searchUsers((network.grpc.UsersProtoBuf.SearchUserArgs) request,
              (io.grpc.stub.StreamObserver<network.grpc.UsersProtoBuf.GrpcUser>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getCreateUserMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              network.grpc.UsersProtoBuf.CreateUserArgs,
              network.grpc.UsersProtoBuf.CreateUserResult>(
                service, METHODID_CREATE_USER)))
        .addMethod(
          getGetUserMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              network.grpc.UsersProtoBuf.GetUserArgs,
              network.grpc.UsersProtoBuf.GetUserResult>(
                service, METHODID_GET_USER)))
        .addMethod(
          getUpdateUserMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              network.grpc.UsersProtoBuf.UpdateUserArgs,
              network.grpc.UsersProtoBuf.UpdateUserResult>(
                service, METHODID_UPDATE_USER)))
        .addMethod(
          getDeleteUserMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              network.grpc.UsersProtoBuf.DeleteUserArgs,
              network.grpc.UsersProtoBuf.DeleteUserResult>(
                service, METHODID_DELETE_USER)))
        .addMethod(
          getSearchUsersMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              network.grpc.UsersProtoBuf.SearchUserArgs,
              network.grpc.UsersProtoBuf.GrpcUser>(
                service, METHODID_SEARCH_USERS)))
        .build();
  }

  private static abstract class UsersBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UsersBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return network.grpc.UsersProtoBuf.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Users");
    }
  }

  private static final class UsersFileDescriptorSupplier
      extends UsersBaseDescriptorSupplier {
    UsersFileDescriptorSupplier() {}
  }

  private static final class UsersMethodDescriptorSupplier
      extends UsersBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    UsersMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (UsersGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UsersFileDescriptorSupplier())
              .addMethod(getCreateUserMethod())
              .addMethod(getGetUserMethod())
              .addMethod(getUpdateUserMethod())
              .addMethod(getDeleteUserMethod())
              .addMethod(getSearchUsersMethod())
              .build();
        }
      }
    }
    return result;
  }
}
