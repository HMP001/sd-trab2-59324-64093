package network.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.62.2)",
    comments = "Source: src/api/grpc/Content.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ContentGrpc {

  private ContentGrpc() {}

  public static final java.lang.String SERVICE_NAME = "Content";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.CreatePostArgs,
      network.grpc.ContentProtoBuf.CreatePostResult> getCreatePostMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "createPost",
      requestType = network.grpc.ContentProtoBuf.CreatePostArgs.class,
      responseType = network.grpc.ContentProtoBuf.CreatePostResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.CreatePostArgs,
      network.grpc.ContentProtoBuf.CreatePostResult> getCreatePostMethod() {
    io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.CreatePostArgs, network.grpc.ContentProtoBuf.CreatePostResult> getCreatePostMethod;
    if ((getCreatePostMethod = ContentGrpc.getCreatePostMethod) == null) {
      synchronized (ContentGrpc.class) {
        if ((getCreatePostMethod = ContentGrpc.getCreatePostMethod) == null) {
          ContentGrpc.getCreatePostMethod = getCreatePostMethod =
              io.grpc.MethodDescriptor.<network.grpc.ContentProtoBuf.CreatePostArgs, network.grpc.ContentProtoBuf.CreatePostResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "createPost"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ContentProtoBuf.CreatePostArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ContentProtoBuf.CreatePostResult.getDefaultInstance()))
              .setSchemaDescriptor(new ContentMethodDescriptorSupplier("createPost"))
              .build();
        }
      }
    }
    return getCreatePostMethod;
  }

  private static volatile io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.GetPostsArgs,
      network.grpc.ContentProtoBuf.GetPostsResult> getGetPostsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getPosts",
      requestType = network.grpc.ContentProtoBuf.GetPostsArgs.class,
      responseType = network.grpc.ContentProtoBuf.GetPostsResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.GetPostsArgs,
      network.grpc.ContentProtoBuf.GetPostsResult> getGetPostsMethod() {
    io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.GetPostsArgs, network.grpc.ContentProtoBuf.GetPostsResult> getGetPostsMethod;
    if ((getGetPostsMethod = ContentGrpc.getGetPostsMethod) == null) {
      synchronized (ContentGrpc.class) {
        if ((getGetPostsMethod = ContentGrpc.getGetPostsMethod) == null) {
          ContentGrpc.getGetPostsMethod = getGetPostsMethod =
              io.grpc.MethodDescriptor.<network.grpc.ContentProtoBuf.GetPostsArgs, network.grpc.ContentProtoBuf.GetPostsResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getPosts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ContentProtoBuf.GetPostsArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ContentProtoBuf.GetPostsResult.getDefaultInstance()))
              .setSchemaDescriptor(new ContentMethodDescriptorSupplier("getPosts"))
              .build();
        }
      }
    }
    return getGetPostsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.GetPostArgs,
      network.grpc.ContentProtoBuf.GrpcPost> getGetPostMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getPost",
      requestType = network.grpc.ContentProtoBuf.GetPostArgs.class,
      responseType = network.grpc.ContentProtoBuf.GrpcPost.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.GetPostArgs,
      network.grpc.ContentProtoBuf.GrpcPost> getGetPostMethod() {
    io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.GetPostArgs, network.grpc.ContentProtoBuf.GrpcPost> getGetPostMethod;
    if ((getGetPostMethod = ContentGrpc.getGetPostMethod) == null) {
      synchronized (ContentGrpc.class) {
        if ((getGetPostMethod = ContentGrpc.getGetPostMethod) == null) {
          ContentGrpc.getGetPostMethod = getGetPostMethod =
              io.grpc.MethodDescriptor.<network.grpc.ContentProtoBuf.GetPostArgs, network.grpc.ContentProtoBuf.GrpcPost>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getPost"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ContentProtoBuf.GetPostArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ContentProtoBuf.GrpcPost.getDefaultInstance()))
              .setSchemaDescriptor(new ContentMethodDescriptorSupplier("getPost"))
              .build();
        }
      }
    }
    return getGetPostMethod;
  }

  private static volatile io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.GetPostAnswersArgs,
      network.grpc.ContentProtoBuf.GetPostsResult> getGetPostAnswersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getPostAnswers",
      requestType = network.grpc.ContentProtoBuf.GetPostAnswersArgs.class,
      responseType = network.grpc.ContentProtoBuf.GetPostsResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.GetPostAnswersArgs,
      network.grpc.ContentProtoBuf.GetPostsResult> getGetPostAnswersMethod() {
    io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.GetPostAnswersArgs, network.grpc.ContentProtoBuf.GetPostsResult> getGetPostAnswersMethod;
    if ((getGetPostAnswersMethod = ContentGrpc.getGetPostAnswersMethod) == null) {
      synchronized (ContentGrpc.class) {
        if ((getGetPostAnswersMethod = ContentGrpc.getGetPostAnswersMethod) == null) {
          ContentGrpc.getGetPostAnswersMethod = getGetPostAnswersMethod =
              io.grpc.MethodDescriptor.<network.grpc.ContentProtoBuf.GetPostAnswersArgs, network.grpc.ContentProtoBuf.GetPostsResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getPostAnswers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ContentProtoBuf.GetPostAnswersArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ContentProtoBuf.GetPostsResult.getDefaultInstance()))
              .setSchemaDescriptor(new ContentMethodDescriptorSupplier("getPostAnswers"))
              .build();
        }
      }
    }
    return getGetPostAnswersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.UpdatePostArgs,
      network.grpc.ContentProtoBuf.GrpcPost> getUpdatePostMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "updatePost",
      requestType = network.grpc.ContentProtoBuf.UpdatePostArgs.class,
      responseType = network.grpc.ContentProtoBuf.GrpcPost.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.UpdatePostArgs,
      network.grpc.ContentProtoBuf.GrpcPost> getUpdatePostMethod() {
    io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.UpdatePostArgs, network.grpc.ContentProtoBuf.GrpcPost> getUpdatePostMethod;
    if ((getUpdatePostMethod = ContentGrpc.getUpdatePostMethod) == null) {
      synchronized (ContentGrpc.class) {
        if ((getUpdatePostMethod = ContentGrpc.getUpdatePostMethod) == null) {
          ContentGrpc.getUpdatePostMethod = getUpdatePostMethod =
              io.grpc.MethodDescriptor.<network.grpc.ContentProtoBuf.UpdatePostArgs, network.grpc.ContentProtoBuf.GrpcPost>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "updatePost"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ContentProtoBuf.UpdatePostArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ContentProtoBuf.GrpcPost.getDefaultInstance()))
              .setSchemaDescriptor(new ContentMethodDescriptorSupplier("updatePost"))
              .build();
        }
      }
    }
    return getUpdatePostMethod;
  }

  private static volatile io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.DeletePostArgs,
      network.grpc.ContentProtoBuf.EmptyMessage> getDeletePostMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "deletePost",
      requestType = network.grpc.ContentProtoBuf.DeletePostArgs.class,
      responseType = network.grpc.ContentProtoBuf.EmptyMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.DeletePostArgs,
      network.grpc.ContentProtoBuf.EmptyMessage> getDeletePostMethod() {
    io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.DeletePostArgs, network.grpc.ContentProtoBuf.EmptyMessage> getDeletePostMethod;
    if ((getDeletePostMethod = ContentGrpc.getDeletePostMethod) == null) {
      synchronized (ContentGrpc.class) {
        if ((getDeletePostMethod = ContentGrpc.getDeletePostMethod) == null) {
          ContentGrpc.getDeletePostMethod = getDeletePostMethod =
              io.grpc.MethodDescriptor.<network.grpc.ContentProtoBuf.DeletePostArgs, network.grpc.ContentProtoBuf.EmptyMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "deletePost"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ContentProtoBuf.DeletePostArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ContentProtoBuf.EmptyMessage.getDefaultInstance()))
              .setSchemaDescriptor(new ContentMethodDescriptorSupplier("deletePost"))
              .build();
        }
      }
    }
    return getDeletePostMethod;
  }

  private static volatile io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.ChangeVoteArgs,
      network.grpc.ContentProtoBuf.EmptyMessage> getUpVotePostMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "upVotePost",
      requestType = network.grpc.ContentProtoBuf.ChangeVoteArgs.class,
      responseType = network.grpc.ContentProtoBuf.EmptyMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.ChangeVoteArgs,
      network.grpc.ContentProtoBuf.EmptyMessage> getUpVotePostMethod() {
    io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.ChangeVoteArgs, network.grpc.ContentProtoBuf.EmptyMessage> getUpVotePostMethod;
    if ((getUpVotePostMethod = ContentGrpc.getUpVotePostMethod) == null) {
      synchronized (ContentGrpc.class) {
        if ((getUpVotePostMethod = ContentGrpc.getUpVotePostMethod) == null) {
          ContentGrpc.getUpVotePostMethod = getUpVotePostMethod =
              io.grpc.MethodDescriptor.<network.grpc.ContentProtoBuf.ChangeVoteArgs, network.grpc.ContentProtoBuf.EmptyMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "upVotePost"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ContentProtoBuf.ChangeVoteArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ContentProtoBuf.EmptyMessage.getDefaultInstance()))
              .setSchemaDescriptor(new ContentMethodDescriptorSupplier("upVotePost"))
              .build();
        }
      }
    }
    return getUpVotePostMethod;
  }

  private static volatile io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.ChangeVoteArgs,
      network.grpc.ContentProtoBuf.EmptyMessage> getRemoveUpVotePostMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "removeUpVotePost",
      requestType = network.grpc.ContentProtoBuf.ChangeVoteArgs.class,
      responseType = network.grpc.ContentProtoBuf.EmptyMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.ChangeVoteArgs,
      network.grpc.ContentProtoBuf.EmptyMessage> getRemoveUpVotePostMethod() {
    io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.ChangeVoteArgs, network.grpc.ContentProtoBuf.EmptyMessage> getRemoveUpVotePostMethod;
    if ((getRemoveUpVotePostMethod = ContentGrpc.getRemoveUpVotePostMethod) == null) {
      synchronized (ContentGrpc.class) {
        if ((getRemoveUpVotePostMethod = ContentGrpc.getRemoveUpVotePostMethod) == null) {
          ContentGrpc.getRemoveUpVotePostMethod = getRemoveUpVotePostMethod =
              io.grpc.MethodDescriptor.<network.grpc.ContentProtoBuf.ChangeVoteArgs, network.grpc.ContentProtoBuf.EmptyMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "removeUpVotePost"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ContentProtoBuf.ChangeVoteArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ContentProtoBuf.EmptyMessage.getDefaultInstance()))
              .setSchemaDescriptor(new ContentMethodDescriptorSupplier("removeUpVotePost"))
              .build();
        }
      }
    }
    return getRemoveUpVotePostMethod;
  }

  private static volatile io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.ChangeVoteArgs,
      network.grpc.ContentProtoBuf.EmptyMessage> getDownVotePostMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "downVotePost",
      requestType = network.grpc.ContentProtoBuf.ChangeVoteArgs.class,
      responseType = network.grpc.ContentProtoBuf.EmptyMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.ChangeVoteArgs,
      network.grpc.ContentProtoBuf.EmptyMessage> getDownVotePostMethod() {
    io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.ChangeVoteArgs, network.grpc.ContentProtoBuf.EmptyMessage> getDownVotePostMethod;
    if ((getDownVotePostMethod = ContentGrpc.getDownVotePostMethod) == null) {
      synchronized (ContentGrpc.class) {
        if ((getDownVotePostMethod = ContentGrpc.getDownVotePostMethod) == null) {
          ContentGrpc.getDownVotePostMethod = getDownVotePostMethod =
              io.grpc.MethodDescriptor.<network.grpc.ContentProtoBuf.ChangeVoteArgs, network.grpc.ContentProtoBuf.EmptyMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "downVotePost"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ContentProtoBuf.ChangeVoteArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ContentProtoBuf.EmptyMessage.getDefaultInstance()))
              .setSchemaDescriptor(new ContentMethodDescriptorSupplier("downVotePost"))
              .build();
        }
      }
    }
    return getDownVotePostMethod;
  }

  private static volatile io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.ChangeVoteArgs,
      network.grpc.ContentProtoBuf.EmptyMessage> getRemoveDownVotePostMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "removeDownVotePost",
      requestType = network.grpc.ContentProtoBuf.ChangeVoteArgs.class,
      responseType = network.grpc.ContentProtoBuf.EmptyMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.ChangeVoteArgs,
      network.grpc.ContentProtoBuf.EmptyMessage> getRemoveDownVotePostMethod() {
    io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.ChangeVoteArgs, network.grpc.ContentProtoBuf.EmptyMessage> getRemoveDownVotePostMethod;
    if ((getRemoveDownVotePostMethod = ContentGrpc.getRemoveDownVotePostMethod) == null) {
      synchronized (ContentGrpc.class) {
        if ((getRemoveDownVotePostMethod = ContentGrpc.getRemoveDownVotePostMethod) == null) {
          ContentGrpc.getRemoveDownVotePostMethod = getRemoveDownVotePostMethod =
              io.grpc.MethodDescriptor.<network.grpc.ContentProtoBuf.ChangeVoteArgs, network.grpc.ContentProtoBuf.EmptyMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "removeDownVotePost"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ContentProtoBuf.ChangeVoteArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ContentProtoBuf.EmptyMessage.getDefaultInstance()))
              .setSchemaDescriptor(new ContentMethodDescriptorSupplier("removeDownVotePost"))
              .build();
        }
      }
    }
    return getRemoveDownVotePostMethod;
  }

  private static volatile io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.GetPostArgs,
      network.grpc.ContentProtoBuf.VoteCountResult> getGetUpVotesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getUpVotes",
      requestType = network.grpc.ContentProtoBuf.GetPostArgs.class,
      responseType = network.grpc.ContentProtoBuf.VoteCountResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.GetPostArgs,
      network.grpc.ContentProtoBuf.VoteCountResult> getGetUpVotesMethod() {
    io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.GetPostArgs, network.grpc.ContentProtoBuf.VoteCountResult> getGetUpVotesMethod;
    if ((getGetUpVotesMethod = ContentGrpc.getGetUpVotesMethod) == null) {
      synchronized (ContentGrpc.class) {
        if ((getGetUpVotesMethod = ContentGrpc.getGetUpVotesMethod) == null) {
          ContentGrpc.getGetUpVotesMethod = getGetUpVotesMethod =
              io.grpc.MethodDescriptor.<network.grpc.ContentProtoBuf.GetPostArgs, network.grpc.ContentProtoBuf.VoteCountResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getUpVotes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ContentProtoBuf.GetPostArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ContentProtoBuf.VoteCountResult.getDefaultInstance()))
              .setSchemaDescriptor(new ContentMethodDescriptorSupplier("getUpVotes"))
              .build();
        }
      }
    }
    return getGetUpVotesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.GetPostArgs,
      network.grpc.ContentProtoBuf.VoteCountResult> getGetDownVotesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getDownVotes",
      requestType = network.grpc.ContentProtoBuf.GetPostArgs.class,
      responseType = network.grpc.ContentProtoBuf.VoteCountResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.GetPostArgs,
      network.grpc.ContentProtoBuf.VoteCountResult> getGetDownVotesMethod() {
    io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.GetPostArgs, network.grpc.ContentProtoBuf.VoteCountResult> getGetDownVotesMethod;
    if ((getGetDownVotesMethod = ContentGrpc.getGetDownVotesMethod) == null) {
      synchronized (ContentGrpc.class) {
        if ((getGetDownVotesMethod = ContentGrpc.getGetDownVotesMethod) == null) {
          ContentGrpc.getGetDownVotesMethod = getGetDownVotesMethod =
              io.grpc.MethodDescriptor.<network.grpc.ContentProtoBuf.GetPostArgs, network.grpc.ContentProtoBuf.VoteCountResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getDownVotes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ContentProtoBuf.GetPostArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ContentProtoBuf.VoteCountResult.getDefaultInstance()))
              .setSchemaDescriptor(new ContentMethodDescriptorSupplier("getDownVotes"))
              .build();
        }
      }
    }
    return getGetDownVotesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.ForgetUserArgs,
      network.grpc.ContentProtoBuf.ForgetUserResult> getForgetUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "forgetUser",
      requestType = network.grpc.ContentProtoBuf.ForgetUserArgs.class,
      responseType = network.grpc.ContentProtoBuf.ForgetUserResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.ForgetUserArgs,
      network.grpc.ContentProtoBuf.ForgetUserResult> getForgetUserMethod() {
    io.grpc.MethodDescriptor<network.grpc.ContentProtoBuf.ForgetUserArgs, network.grpc.ContentProtoBuf.ForgetUserResult> getForgetUserMethod;
    if ((getForgetUserMethod = ContentGrpc.getForgetUserMethod) == null) {
      synchronized (ContentGrpc.class) {
        if ((getForgetUserMethod = ContentGrpc.getForgetUserMethod) == null) {
          ContentGrpc.getForgetUserMethod = getForgetUserMethod =
              io.grpc.MethodDescriptor.<network.grpc.ContentProtoBuf.ForgetUserArgs, network.grpc.ContentProtoBuf.ForgetUserResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "forgetUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ContentProtoBuf.ForgetUserArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  network.grpc.ContentProtoBuf.ForgetUserResult.getDefaultInstance()))
              .setSchemaDescriptor(new ContentMethodDescriptorSupplier("forgetUser"))
              .build();
        }
      }
    }
    return getForgetUserMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ContentStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ContentStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ContentStub>() {
        @java.lang.Override
        public ContentStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ContentStub(channel, callOptions);
        }
      };
    return ContentStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ContentBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ContentBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ContentBlockingStub>() {
        @java.lang.Override
        public ContentBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ContentBlockingStub(channel, callOptions);
        }
      };
    return ContentBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ContentFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ContentFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ContentFutureStub>() {
        @java.lang.Override
        public ContentFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ContentFutureStub(channel, callOptions);
        }
      };
    return ContentFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void createPost(network.grpc.ContentProtoBuf.CreatePostArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.CreatePostResult> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreatePostMethod(), responseObserver);
    }

    /**
     */
    default void getPosts(network.grpc.ContentProtoBuf.GetPostsArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.GetPostsResult> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetPostsMethod(), responseObserver);
    }

    /**
     */
    default void getPost(network.grpc.ContentProtoBuf.GetPostArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.GrpcPost> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetPostMethod(), responseObserver);
    }

    /**
     */
    default void getPostAnswers(network.grpc.ContentProtoBuf.GetPostAnswersArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.GetPostsResult> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetPostAnswersMethod(), responseObserver);
    }

    /**
     */
    default void updatePost(network.grpc.ContentProtoBuf.UpdatePostArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.GrpcPost> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdatePostMethod(), responseObserver);
    }

    /**
     */
    default void deletePost(network.grpc.ContentProtoBuf.DeletePostArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.EmptyMessage> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeletePostMethod(), responseObserver);
    }

    /**
     */
    default void upVotePost(network.grpc.ContentProtoBuf.ChangeVoteArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.EmptyMessage> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpVotePostMethod(), responseObserver);
    }

    /**
     */
    default void removeUpVotePost(network.grpc.ContentProtoBuf.ChangeVoteArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.EmptyMessage> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRemoveUpVotePostMethod(), responseObserver);
    }

    /**
     */
    default void downVotePost(network.grpc.ContentProtoBuf.ChangeVoteArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.EmptyMessage> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDownVotePostMethod(), responseObserver);
    }

    /**
     */
    default void removeDownVotePost(network.grpc.ContentProtoBuf.ChangeVoteArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.EmptyMessage> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRemoveDownVotePostMethod(), responseObserver);
    }

    /**
     */
    default void getUpVotes(network.grpc.ContentProtoBuf.GetPostArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.VoteCountResult> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetUpVotesMethod(), responseObserver);
    }

    /**
     */
    default void getDownVotes(network.grpc.ContentProtoBuf.GetPostArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.VoteCountResult> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetDownVotesMethod(), responseObserver);
    }

    /**
     */
    default void forgetUser(network.grpc.ContentProtoBuf.ForgetUserArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.ForgetUserResult> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getForgetUserMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Content.
   */
  public static abstract class ContentImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ContentGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Content.
   */
  public static final class ContentStub
      extends io.grpc.stub.AbstractAsyncStub<ContentStub> {
    private ContentStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ContentStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ContentStub(channel, callOptions);
    }

    /**
     */
    public void createPost(network.grpc.ContentProtoBuf.CreatePostArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.CreatePostResult> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreatePostMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getPosts(network.grpc.ContentProtoBuf.GetPostsArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.GetPostsResult> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetPostsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getPost(network.grpc.ContentProtoBuf.GetPostArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.GrpcPost> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetPostMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getPostAnswers(network.grpc.ContentProtoBuf.GetPostAnswersArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.GetPostsResult> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetPostAnswersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updatePost(network.grpc.ContentProtoBuf.UpdatePostArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.GrpcPost> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdatePostMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deletePost(network.grpc.ContentProtoBuf.DeletePostArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.EmptyMessage> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeletePostMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void upVotePost(network.grpc.ContentProtoBuf.ChangeVoteArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.EmptyMessage> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpVotePostMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void removeUpVotePost(network.grpc.ContentProtoBuf.ChangeVoteArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.EmptyMessage> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRemoveUpVotePostMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void downVotePost(network.grpc.ContentProtoBuf.ChangeVoteArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.EmptyMessage> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDownVotePostMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void removeDownVotePost(network.grpc.ContentProtoBuf.ChangeVoteArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.EmptyMessage> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRemoveDownVotePostMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getUpVotes(network.grpc.ContentProtoBuf.GetPostArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.VoteCountResult> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetUpVotesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDownVotes(network.grpc.ContentProtoBuf.GetPostArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.VoteCountResult> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetDownVotesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void forgetUser(network.grpc.ContentProtoBuf.ForgetUserArgs request,
        io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.ForgetUserResult> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getForgetUserMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Content.
   */
  public static final class ContentBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ContentBlockingStub> {
    private ContentBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ContentBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ContentBlockingStub(channel, callOptions);
    }

    /**
     */
    public network.grpc.ContentProtoBuf.CreatePostResult createPost(network.grpc.ContentProtoBuf.CreatePostArgs request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreatePostMethod(), getCallOptions(), request);
    }

    /**
     */
    public network.grpc.ContentProtoBuf.GetPostsResult getPosts(network.grpc.ContentProtoBuf.GetPostsArgs request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetPostsMethod(), getCallOptions(), request);
    }

    /**
     */
    public network.grpc.ContentProtoBuf.GrpcPost getPost(network.grpc.ContentProtoBuf.GetPostArgs request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetPostMethod(), getCallOptions(), request);
    }

    /**
     */
    public network.grpc.ContentProtoBuf.GetPostsResult getPostAnswers(network.grpc.ContentProtoBuf.GetPostAnswersArgs request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetPostAnswersMethod(), getCallOptions(), request);
    }

    /**
     */
    public network.grpc.ContentProtoBuf.GrpcPost updatePost(network.grpc.ContentProtoBuf.UpdatePostArgs request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdatePostMethod(), getCallOptions(), request);
    }

    /**
     */
    public network.grpc.ContentProtoBuf.EmptyMessage deletePost(network.grpc.ContentProtoBuf.DeletePostArgs request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeletePostMethod(), getCallOptions(), request);
    }

    /**
     */
    public network.grpc.ContentProtoBuf.EmptyMessage upVotePost(network.grpc.ContentProtoBuf.ChangeVoteArgs request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpVotePostMethod(), getCallOptions(), request);
    }

    /**
     */
    public network.grpc.ContentProtoBuf.EmptyMessage removeUpVotePost(network.grpc.ContentProtoBuf.ChangeVoteArgs request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRemoveUpVotePostMethod(), getCallOptions(), request);
    }

    /**
     */
    public network.grpc.ContentProtoBuf.EmptyMessage downVotePost(network.grpc.ContentProtoBuf.ChangeVoteArgs request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDownVotePostMethod(), getCallOptions(), request);
    }

    /**
     */
    public network.grpc.ContentProtoBuf.EmptyMessage removeDownVotePost(network.grpc.ContentProtoBuf.ChangeVoteArgs request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRemoveDownVotePostMethod(), getCallOptions(), request);
    }

    /**
     */
    public network.grpc.ContentProtoBuf.VoteCountResult getUpVotes(network.grpc.ContentProtoBuf.GetPostArgs request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetUpVotesMethod(), getCallOptions(), request);
    }

    /**
     */
    public network.grpc.ContentProtoBuf.VoteCountResult getDownVotes(network.grpc.ContentProtoBuf.GetPostArgs request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetDownVotesMethod(), getCallOptions(), request);
    }

    /**
     */
    public network.grpc.ContentProtoBuf.ForgetUserResult forgetUser(network.grpc.ContentProtoBuf.ForgetUserArgs request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getForgetUserMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Content.
   */
  public static final class ContentFutureStub
      extends io.grpc.stub.AbstractFutureStub<ContentFutureStub> {
    private ContentFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ContentFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ContentFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<network.grpc.ContentProtoBuf.CreatePostResult> createPost(
        network.grpc.ContentProtoBuf.CreatePostArgs request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreatePostMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<network.grpc.ContentProtoBuf.GetPostsResult> getPosts(
        network.grpc.ContentProtoBuf.GetPostsArgs request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetPostsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<network.grpc.ContentProtoBuf.GrpcPost> getPost(
        network.grpc.ContentProtoBuf.GetPostArgs request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetPostMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<network.grpc.ContentProtoBuf.GetPostsResult> getPostAnswers(
        network.grpc.ContentProtoBuf.GetPostAnswersArgs request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetPostAnswersMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<network.grpc.ContentProtoBuf.GrpcPost> updatePost(
        network.grpc.ContentProtoBuf.UpdatePostArgs request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdatePostMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<network.grpc.ContentProtoBuf.EmptyMessage> deletePost(
        network.grpc.ContentProtoBuf.DeletePostArgs request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeletePostMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<network.grpc.ContentProtoBuf.EmptyMessage> upVotePost(
        network.grpc.ContentProtoBuf.ChangeVoteArgs request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpVotePostMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<network.grpc.ContentProtoBuf.EmptyMessage> removeUpVotePost(
        network.grpc.ContentProtoBuf.ChangeVoteArgs request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRemoveUpVotePostMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<network.grpc.ContentProtoBuf.EmptyMessage> downVotePost(
        network.grpc.ContentProtoBuf.ChangeVoteArgs request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDownVotePostMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<network.grpc.ContentProtoBuf.EmptyMessage> removeDownVotePost(
        network.grpc.ContentProtoBuf.ChangeVoteArgs request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRemoveDownVotePostMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<network.grpc.ContentProtoBuf.VoteCountResult> getUpVotes(
        network.grpc.ContentProtoBuf.GetPostArgs request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetUpVotesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<network.grpc.ContentProtoBuf.VoteCountResult> getDownVotes(
        network.grpc.ContentProtoBuf.GetPostArgs request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetDownVotesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<network.grpc.ContentProtoBuf.ForgetUserResult> forgetUser(
        network.grpc.ContentProtoBuf.ForgetUserArgs request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getForgetUserMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_POST = 0;
  private static final int METHODID_GET_POSTS = 1;
  private static final int METHODID_GET_POST = 2;
  private static final int METHODID_GET_POST_ANSWERS = 3;
  private static final int METHODID_UPDATE_POST = 4;
  private static final int METHODID_DELETE_POST = 5;
  private static final int METHODID_UP_VOTE_POST = 6;
  private static final int METHODID_REMOVE_UP_VOTE_POST = 7;
  private static final int METHODID_DOWN_VOTE_POST = 8;
  private static final int METHODID_REMOVE_DOWN_VOTE_POST = 9;
  private static final int METHODID_GET_UP_VOTES = 10;
  private static final int METHODID_GET_DOWN_VOTES = 11;
  private static final int METHODID_FORGET_USER = 12;

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
        case METHODID_CREATE_POST:
          serviceImpl.createPost((network.grpc.ContentProtoBuf.CreatePostArgs) request,
              (io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.CreatePostResult>) responseObserver);
          break;
        case METHODID_GET_POSTS:
          serviceImpl.getPosts((network.grpc.ContentProtoBuf.GetPostsArgs) request,
              (io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.GetPostsResult>) responseObserver);
          break;
        case METHODID_GET_POST:
          serviceImpl.getPost((network.grpc.ContentProtoBuf.GetPostArgs) request,
              (io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.GrpcPost>) responseObserver);
          break;
        case METHODID_GET_POST_ANSWERS:
          serviceImpl.getPostAnswers((network.grpc.ContentProtoBuf.GetPostAnswersArgs) request,
              (io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.GetPostsResult>) responseObserver);
          break;
        case METHODID_UPDATE_POST:
          serviceImpl.updatePost((network.grpc.ContentProtoBuf.UpdatePostArgs) request,
              (io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.GrpcPost>) responseObserver);
          break;
        case METHODID_DELETE_POST:
          serviceImpl.deletePost((network.grpc.ContentProtoBuf.DeletePostArgs) request,
              (io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.EmptyMessage>) responseObserver);
          break;
        case METHODID_UP_VOTE_POST:
          serviceImpl.upVotePost((network.grpc.ContentProtoBuf.ChangeVoteArgs) request,
              (io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.EmptyMessage>) responseObserver);
          break;
        case METHODID_REMOVE_UP_VOTE_POST:
          serviceImpl.removeUpVotePost((network.grpc.ContentProtoBuf.ChangeVoteArgs) request,
              (io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.EmptyMessage>) responseObserver);
          break;
        case METHODID_DOWN_VOTE_POST:
          serviceImpl.downVotePost((network.grpc.ContentProtoBuf.ChangeVoteArgs) request,
              (io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.EmptyMessage>) responseObserver);
          break;
        case METHODID_REMOVE_DOWN_VOTE_POST:
          serviceImpl.removeDownVotePost((network.grpc.ContentProtoBuf.ChangeVoteArgs) request,
              (io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.EmptyMessage>) responseObserver);
          break;
        case METHODID_GET_UP_VOTES:
          serviceImpl.getUpVotes((network.grpc.ContentProtoBuf.GetPostArgs) request,
              (io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.VoteCountResult>) responseObserver);
          break;
        case METHODID_GET_DOWN_VOTES:
          serviceImpl.getDownVotes((network.grpc.ContentProtoBuf.GetPostArgs) request,
              (io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.VoteCountResult>) responseObserver);
          break;
        case METHODID_FORGET_USER:
          serviceImpl.forgetUser((network.grpc.ContentProtoBuf.ForgetUserArgs) request,
              (io.grpc.stub.StreamObserver<network.grpc.ContentProtoBuf.ForgetUserResult>) responseObserver);
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
          getCreatePostMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              network.grpc.ContentProtoBuf.CreatePostArgs,
              network.grpc.ContentProtoBuf.CreatePostResult>(
                service, METHODID_CREATE_POST)))
        .addMethod(
          getGetPostsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              network.grpc.ContentProtoBuf.GetPostsArgs,
              network.grpc.ContentProtoBuf.GetPostsResult>(
                service, METHODID_GET_POSTS)))
        .addMethod(
          getGetPostMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              network.grpc.ContentProtoBuf.GetPostArgs,
              network.grpc.ContentProtoBuf.GrpcPost>(
                service, METHODID_GET_POST)))
        .addMethod(
          getGetPostAnswersMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              network.grpc.ContentProtoBuf.GetPostAnswersArgs,
              network.grpc.ContentProtoBuf.GetPostsResult>(
                service, METHODID_GET_POST_ANSWERS)))
        .addMethod(
          getUpdatePostMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              network.grpc.ContentProtoBuf.UpdatePostArgs,
              network.grpc.ContentProtoBuf.GrpcPost>(
                service, METHODID_UPDATE_POST)))
        .addMethod(
          getDeletePostMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              network.grpc.ContentProtoBuf.DeletePostArgs,
              network.grpc.ContentProtoBuf.EmptyMessage>(
                service, METHODID_DELETE_POST)))
        .addMethod(
          getUpVotePostMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              network.grpc.ContentProtoBuf.ChangeVoteArgs,
              network.grpc.ContentProtoBuf.EmptyMessage>(
                service, METHODID_UP_VOTE_POST)))
        .addMethod(
          getRemoveUpVotePostMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              network.grpc.ContentProtoBuf.ChangeVoteArgs,
              network.grpc.ContentProtoBuf.EmptyMessage>(
                service, METHODID_REMOVE_UP_VOTE_POST)))
        .addMethod(
          getDownVotePostMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              network.grpc.ContentProtoBuf.ChangeVoteArgs,
              network.grpc.ContentProtoBuf.EmptyMessage>(
                service, METHODID_DOWN_VOTE_POST)))
        .addMethod(
          getRemoveDownVotePostMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              network.grpc.ContentProtoBuf.ChangeVoteArgs,
              network.grpc.ContentProtoBuf.EmptyMessage>(
                service, METHODID_REMOVE_DOWN_VOTE_POST)))
        .addMethod(
          getGetUpVotesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              network.grpc.ContentProtoBuf.GetPostArgs,
              network.grpc.ContentProtoBuf.VoteCountResult>(
                service, METHODID_GET_UP_VOTES)))
        .addMethod(
          getGetDownVotesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              network.grpc.ContentProtoBuf.GetPostArgs,
              network.grpc.ContentProtoBuf.VoteCountResult>(
                service, METHODID_GET_DOWN_VOTES)))
        .addMethod(
          getForgetUserMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              network.grpc.ContentProtoBuf.ForgetUserArgs,
              network.grpc.ContentProtoBuf.ForgetUserResult>(
                service, METHODID_FORGET_USER)))
        .build();
  }

  private static abstract class ContentBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ContentBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return network.grpc.ContentProtoBuf.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Content");
    }
  }

  private static final class ContentFileDescriptorSupplier
      extends ContentBaseDescriptorSupplier {
    ContentFileDescriptorSupplier() {}
  }

  private static final class ContentMethodDescriptorSupplier
      extends ContentBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ContentMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (ContentGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ContentFileDescriptorSupplier())
              .addMethod(getCreatePostMethod())
              .addMethod(getGetPostsMethod())
              .addMethod(getGetPostMethod())
              .addMethod(getGetPostAnswersMethod())
              .addMethod(getUpdatePostMethod())
              .addMethod(getDeletePostMethod())
              .addMethod(getUpVotePostMethod())
              .addMethod(getRemoveUpVotePostMethod())
              .addMethod(getDownVotePostMethod())
              .addMethod(getRemoveDownVotePostMethod())
              .addMethod(getGetUpVotesMethod())
              .addMethod(getGetDownVotesMethod())
              .addMethod(getForgetUserMethod())
              .build();
        }
      }
    }
    return result;
  }
}
