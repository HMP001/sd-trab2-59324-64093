package network.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.62.2)",
    comments = "Source: src/api/grpc/Image.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ImageGrpc {

  private ImageGrpc() {}

  public static final java.lang.String SERVICE_NAME = "Image";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<network.grpc.ImageProtoBuf.CreateImageArgs,
      network.grpc.ImageProtoBuf.CreateImageResult> getCreateImageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "createImage",
      requestType = network.grpc.ImageProtoBuf.CreateImageArgs.class,
      responseType = network.grpc.ImageProtoBuf.CreateImageResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<network.grpc.ImageProtoBuf.CreateImageArgs,
      network.grpc.ImageProtoBuf.CreateImageResult> getCreateImageMethod() {
    io.grpc.MethodDescriptor<network.grpc.ImageProtoBuf.CreateImageArgs, network.grpc.ImageProtoBuf.CreateImageResult> getCreateImageMethod;
    if ((getCreateImageMethod = ImageGrpc.getCreateImageMethod) == null) {
      synchronized (ImageGrpc.class) {
        if ((getCreateImageMethod = ImageGrpc.getCreateImageMethod) == null) {
          ImageGrpc.getCreateImageMethod = getCreateImageMethod =
              io.grpc.MethodDescriptor.<network.grpc.ImageProtoBuf.CreateImageArgs, network.grpc.ImageProtoBuf.CreateImageResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "createImage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ImageProtoBuf.CreateImageArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ImageProtoBuf.CreateImageResult.getDefaultInstance()))
              .setSchemaDescriptor(new ImageMethodDescriptorSupplier("createImage"))
              .build();
        }
      }
    }
    return getCreateImageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<network.grpc.ImageProtoBuf.GetImageArgs,
      network.grpc.ImageProtoBuf.GetImageResult> getGetImageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getImage",
      requestType = network.grpc.ImageProtoBuf.GetImageArgs.class,
      responseType = network.grpc.ImageProtoBuf.GetImageResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<network.grpc.ImageProtoBuf.GetImageArgs,
      network.grpc.ImageProtoBuf.GetImageResult> getGetImageMethod() {
    io.grpc.MethodDescriptor<network.grpc.ImageProtoBuf.GetImageArgs, network.grpc.ImageProtoBuf.GetImageResult> getGetImageMethod;
    if ((getGetImageMethod = ImageGrpc.getGetImageMethod) == null) {
      synchronized (ImageGrpc.class) {
        if ((getGetImageMethod = ImageGrpc.getGetImageMethod) == null) {
          ImageGrpc.getGetImageMethod = getGetImageMethod =
              io.grpc.MethodDescriptor.<network.grpc.ImageProtoBuf.GetImageArgs, network.grpc.ImageProtoBuf.GetImageResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getImage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ImageProtoBuf.GetImageArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ImageProtoBuf.GetImageResult.getDefaultInstance()))
              .setSchemaDescriptor(new ImageMethodDescriptorSupplier("getImage"))
              .build();
        }
      }
    }
    return getGetImageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<network.grpc.ImageProtoBuf.DeleteImageArgs,
      network.grpc.ImageProtoBuf.DeleteImageResult> getDeleteImageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "deleteImage",
      requestType = network.grpc.ImageProtoBuf.DeleteImageArgs.class,
      responseType = network.grpc.ImageProtoBuf.DeleteImageResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<network.grpc.ImageProtoBuf.DeleteImageArgs,
      network.grpc.ImageProtoBuf.DeleteImageResult> getDeleteImageMethod() {
    io.grpc.MethodDescriptor<network.grpc.ImageProtoBuf.DeleteImageArgs, network.grpc.ImageProtoBuf.DeleteImageResult> getDeleteImageMethod;
    if ((getDeleteImageMethod = ImageGrpc.getDeleteImageMethod) == null) {
      synchronized (ImageGrpc.class) {
        if ((getDeleteImageMethod = ImageGrpc.getDeleteImageMethod) == null) {
          ImageGrpc.getDeleteImageMethod = getDeleteImageMethod =
              io.grpc.MethodDescriptor.<network.grpc.ImageProtoBuf.DeleteImageArgs, network.grpc.ImageProtoBuf.DeleteImageResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "deleteImage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ImageProtoBuf.DeleteImageArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ImageProtoBuf.DeleteImageResult.getDefaultInstance()))
              .setSchemaDescriptor(new ImageMethodDescriptorSupplier("deleteImage"))
              .build();
        }
      }
    }
    return getDeleteImageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<network.grpc.ImageProtoBuf.DelUponUsrRemArgs,
      network.grpc.ImageProtoBuf.DelUponUsrRemResult> getDeleteImageUponUserOrPostRemovalMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "deleteImageUponUserOrPostRemoval",
      requestType = network.grpc.ImageProtoBuf.DelUponUsrRemArgs.class,
      responseType = network.grpc.ImageProtoBuf.DelUponUsrRemResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<network.grpc.ImageProtoBuf.DelUponUsrRemArgs,
      network.grpc.ImageProtoBuf.DelUponUsrRemResult> getDeleteImageUponUserOrPostRemovalMethod() {
    io.grpc.MethodDescriptor<network.grpc.ImageProtoBuf.DelUponUsrRemArgs, network.grpc.ImageProtoBuf.DelUponUsrRemResult> getDeleteImageUponUserOrPostRemovalMethod;
    if ((getDeleteImageUponUserOrPostRemovalMethod = ImageGrpc.getDeleteImageUponUserOrPostRemovalMethod) == null) {
      synchronized (ImageGrpc.class) {
        if ((getDeleteImageUponUserOrPostRemovalMethod = ImageGrpc.getDeleteImageUponUserOrPostRemovalMethod) == null) {
          ImageGrpc.getDeleteImageUponUserOrPostRemovalMethod = getDeleteImageUponUserOrPostRemovalMethod =
              io.grpc.MethodDescriptor.<network.grpc.ImageProtoBuf.DelUponUsrRemArgs, network.grpc.ImageProtoBuf.DelUponUsrRemResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "deleteImageUponUserOrPostRemoval"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ImageProtoBuf.DelUponUsrRemArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ImageProtoBuf.DelUponUsrRemResult.getDefaultInstance()))
              .setSchemaDescriptor(new ImageMethodDescriptorSupplier("deleteImageUponUserOrPostRemoval"))
              .build();
        }
      }
    }
    return getDeleteImageUponUserOrPostRemovalMethod;
  }

  private static volatile io.grpc.MethodDescriptor<network.grpc.ImageProtoBuf.TeardownArgs,
      network.grpc.ImageProtoBuf.TeardownResult> getTeardownMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "teardown",
      requestType = network.grpc.ImageProtoBuf.TeardownArgs.class,
      responseType = network.grpc.ImageProtoBuf.TeardownResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<network.grpc.ImageProtoBuf.TeardownArgs,
      network.grpc.ImageProtoBuf.TeardownResult> getTeardownMethod() {
    io.grpc.MethodDescriptor<network.grpc.ImageProtoBuf.TeardownArgs, network.grpc.ImageProtoBuf.TeardownResult> getTeardownMethod;
    if ((getTeardownMethod = ImageGrpc.getTeardownMethod) == null) {
      synchronized (ImageGrpc.class) {
        if ((getTeardownMethod = ImageGrpc.getTeardownMethod) == null) {
          ImageGrpc.getTeardownMethod = getTeardownMethod =
              io.grpc.MethodDescriptor.<network.grpc.ImageProtoBuf.TeardownArgs, network.grpc.ImageProtoBuf.TeardownResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "teardown"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ImageProtoBuf.TeardownArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ImageProtoBuf.TeardownResult.getDefaultInstance()))
              .setSchemaDescriptor(new ImageMethodDescriptorSupplier("teardown"))
              .build();
        }
      }
    }
    return getTeardownMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ImageStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ImageStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ImageStub>() {
        @java.lang.Override
        public ImageStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ImageStub(channel, callOptions);
        }
      };
    return ImageStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ImageBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ImageBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ImageBlockingStub>() {
        @java.lang.Override
        public ImageBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ImageBlockingStub(channel, callOptions);
        }
      };
    return ImageBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ImageFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ImageFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ImageFutureStub>() {
        @java.lang.Override
        public ImageFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ImageFutureStub(channel, callOptions);
        }
      };
    return ImageFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void createImage(network.grpc.ImageProtoBuf.CreateImageArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ImageProtoBuf.CreateImageResult> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateImageMethod(), responseObserver);
    }

    /**
     */
    default void getImage(network.grpc.ImageProtoBuf.GetImageArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ImageProtoBuf.GetImageResult> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetImageMethod(), responseObserver);
    }

    /**
     */
    default void deleteImage(network.grpc.ImageProtoBuf.DeleteImageArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ImageProtoBuf.DeleteImageResult> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteImageMethod(), responseObserver);
    }

    /**
     */
    default void deleteImageUponUserOrPostRemoval(network.grpc.ImageProtoBuf.DelUponUsrRemArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ImageProtoBuf.DelUponUsrRemResult> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteImageUponUserOrPostRemovalMethod(), responseObserver);
    }

    /**
     */
    default void teardown(network.grpc.ImageProtoBuf.TeardownArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ImageProtoBuf.TeardownResult> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTeardownMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Image.
   */
  public static abstract class ImageImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ImageGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Image.
   */
  public static final class ImageStub
      extends io.grpc.stub.AbstractAsyncStub<ImageStub> {
    private ImageStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ImageStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ImageStub(channel, callOptions);
    }

    /**
     */
    public void createImage(network.grpc.ImageProtoBuf.CreateImageArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ImageProtoBuf.CreateImageResult> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateImageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getImage(network.grpc.ImageProtoBuf.GetImageArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ImageProtoBuf.GetImageResult> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetImageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteImage(network.grpc.ImageProtoBuf.DeleteImageArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ImageProtoBuf.DeleteImageResult> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteImageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteImageUponUserOrPostRemoval(network.grpc.ImageProtoBuf.DelUponUsrRemArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ImageProtoBuf.DelUponUsrRemResult> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteImageUponUserOrPostRemovalMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void teardown(network.grpc.ImageProtoBuf.TeardownArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ImageProtoBuf.TeardownResult> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTeardownMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Image.
   */
  public static final class ImageBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ImageBlockingStub> {
    private ImageBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ImageBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ImageBlockingStub(channel, callOptions);
    }

    /**
     */
    public network.grpc.ImageProtoBuf.CreateImageResult createImage(network.grpc.ImageProtoBuf.CreateImageArgs request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateImageMethod(), getCallOptions(), request);
    }

    /**
     */
    public network.grpc.ImageProtoBuf.GetImageResult getImage(network.grpc.ImageProtoBuf.GetImageArgs request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetImageMethod(), getCallOptions(), request);
    }

    /**
     */
    public network.grpc.ImageProtoBuf.DeleteImageResult deleteImage(network.grpc.ImageProtoBuf.DeleteImageArgs request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteImageMethod(), getCallOptions(), request);
    }

    /**
     */
    public network.grpc.ImageProtoBuf.DelUponUsrRemResult deleteImageUponUserOrPostRemoval(network.grpc.ImageProtoBuf.DelUponUsrRemArgs request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteImageUponUserOrPostRemovalMethod(), getCallOptions(), request);
    }

    /**
     */
    public network.grpc.ImageProtoBuf.TeardownResult teardown(network.grpc.ImageProtoBuf.TeardownArgs request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTeardownMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Image.
   */
  public static final class ImageFutureStub
      extends io.grpc.stub.AbstractFutureStub<ImageFutureStub> {
    private ImageFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ImageFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ImageFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<network.grpc.ImageProtoBuf.CreateImageResult> createImage(
        network.grpc.ImageProtoBuf.CreateImageArgs request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateImageMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<network.grpc.ImageProtoBuf.GetImageResult> getImage(
        network.grpc.ImageProtoBuf.GetImageArgs request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetImageMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<network.grpc.ImageProtoBuf.DeleteImageResult> deleteImage(
        network.grpc.ImageProtoBuf.DeleteImageArgs request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteImageMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<network.grpc.ImageProtoBuf.DelUponUsrRemResult> deleteImageUponUserOrPostRemoval(
        network.grpc.ImageProtoBuf.DelUponUsrRemArgs request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteImageUponUserOrPostRemovalMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<network.grpc.ImageProtoBuf.TeardownResult> teardown(
        network.grpc.ImageProtoBuf.TeardownArgs request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTeardownMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_IMAGE = 0;
  private static final int METHODID_GET_IMAGE = 1;
  private static final int METHODID_DELETE_IMAGE = 2;
  private static final int METHODID_DELETE_IMAGE_UPON_USER_OR_POST_REMOVAL = 3;
  private static final int METHODID_TEARDOWN = 4;

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
        case METHODID_CREATE_IMAGE:
          serviceImpl.createImage((network.grpc.ImageProtoBuf.CreateImageArgs) request,
              (io.grpc.stub.StreamObserver<network.grpc.ImageProtoBuf.CreateImageResult>) responseObserver);
          break;
        case METHODID_GET_IMAGE:
          serviceImpl.getImage((network.grpc.ImageProtoBuf.GetImageArgs) request,
              (io.grpc.stub.StreamObserver<network.grpc.ImageProtoBuf.GetImageResult>) responseObserver);
          break;
        case METHODID_DELETE_IMAGE:
          serviceImpl.deleteImage((network.grpc.ImageProtoBuf.DeleteImageArgs) request,
              (io.grpc.stub.StreamObserver<network.grpc.ImageProtoBuf.DeleteImageResult>) responseObserver);
          break;
        case METHODID_DELETE_IMAGE_UPON_USER_OR_POST_REMOVAL:
          serviceImpl.deleteImageUponUserOrPostRemoval((network.grpc.ImageProtoBuf.DelUponUsrRemArgs) request,
              (io.grpc.stub.StreamObserver<network.grpc.ImageProtoBuf.DelUponUsrRemResult>) responseObserver);
          break;
        case METHODID_TEARDOWN:
          serviceImpl.teardown((network.grpc.ImageProtoBuf.TeardownArgs) request,
              (io.grpc.stub.StreamObserver<network.grpc.ImageProtoBuf.TeardownResult>) responseObserver);
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
          getCreateImageMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              network.grpc.ImageProtoBuf.CreateImageArgs,
              network.grpc.ImageProtoBuf.CreateImageResult>(
                service, METHODID_CREATE_IMAGE)))
        .addMethod(
          getGetImageMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              network.grpc.ImageProtoBuf.GetImageArgs,
              network.grpc.ImageProtoBuf.GetImageResult>(
                service, METHODID_GET_IMAGE)))
        .addMethod(
          getDeleteImageMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              network.grpc.ImageProtoBuf.DeleteImageArgs,
              network.grpc.ImageProtoBuf.DeleteImageResult>(
                service, METHODID_DELETE_IMAGE)))
        .addMethod(
          getDeleteImageUponUserOrPostRemovalMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              network.grpc.ImageProtoBuf.DelUponUsrRemArgs,
              network.grpc.ImageProtoBuf.DelUponUsrRemResult>(
                service, METHODID_DELETE_IMAGE_UPON_USER_OR_POST_REMOVAL)))
        .addMethod(
          getTeardownMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              network.grpc.ImageProtoBuf.TeardownArgs,
              network.grpc.ImageProtoBuf.TeardownResult>(
                service, METHODID_TEARDOWN)))
        .build();
  }

  private static abstract class ImageBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ImageBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return network.grpc.ImageProtoBuf.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Image");
    }
  }

  private static final class ImageFileDescriptorSupplier
      extends ImageBaseDescriptorSupplier {
    ImageFileDescriptorSupplier() {}
  }

  private static final class ImageMethodDescriptorSupplier
      extends ImageBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ImageMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (ImageGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ImageFileDescriptorSupplier())
              .addMethod(getCreateImageMethod())
              .addMethod(getGetImageMethod())
              .addMethod(getDeleteImageMethod())
              .addMethod(getDeleteImageUponUserOrPostRemovalMethod())
              .addMethod(getTeardownMethod())
              .build();
        }
      }
    }
    return result;
  }
}
