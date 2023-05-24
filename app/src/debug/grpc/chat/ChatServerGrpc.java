package chat;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.0)",
    comments = "Source: chat.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ChatServerGrpc {

  private ChatServerGrpc() {}

  public static final String SERVICE_NAME = "chat.ChatServer";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<chat.Chat.ClientMessage,
      chat.Chat.ServerMessage> getHandleCommunicationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "HandleCommunication",
      requestType = chat.Chat.ClientMessage.class,
      responseType = chat.Chat.ServerMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<chat.Chat.ClientMessage,
      chat.Chat.ServerMessage> getHandleCommunicationMethod() {
    io.grpc.MethodDescriptor<chat.Chat.ClientMessage, chat.Chat.ServerMessage> getHandleCommunicationMethod;
    if ((getHandleCommunicationMethod = ChatServerGrpc.getHandleCommunicationMethod) == null) {
      synchronized (ChatServerGrpc.class) {
        if ((getHandleCommunicationMethod = ChatServerGrpc.getHandleCommunicationMethod) == null) {
          ChatServerGrpc.getHandleCommunicationMethod = getHandleCommunicationMethod =
              io.grpc.MethodDescriptor.<chat.Chat.ClientMessage, chat.Chat.ServerMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "HandleCommunication"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  chat.Chat.ClientMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  chat.Chat.ServerMessage.getDefaultInstance()))
              .build();
        }
      }
    }
    return getHandleCommunicationMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ChatServerStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ChatServerStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ChatServerStub>() {
        @java.lang.Override
        public ChatServerStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ChatServerStub(channel, callOptions);
        }
      };
    return ChatServerStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ChatServerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ChatServerBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ChatServerBlockingStub>() {
        @java.lang.Override
        public ChatServerBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ChatServerBlockingStub(channel, callOptions);
        }
      };
    return ChatServerBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ChatServerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ChatServerFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ChatServerFutureStub>() {
        @java.lang.Override
        public ChatServerFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ChatServerFutureStub(channel, callOptions);
        }
      };
    return ChatServerFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     * <pre>
     * Bidirectional communication stream between client and server
     * </pre>
     */
    default io.grpc.stub.StreamObserver<chat.Chat.ClientMessage> handleCommunication(
        io.grpc.stub.StreamObserver<chat.Chat.ServerMessage> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getHandleCommunicationMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ChatServer.
   */
  public static abstract class ChatServerImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ChatServerGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ChatServer.
   */
  public static final class ChatServerStub
      extends io.grpc.stub.AbstractAsyncStub<ChatServerStub> {
    private ChatServerStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatServerStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ChatServerStub(channel, callOptions);
    }

    /**
     * <pre>
     * Bidirectional communication stream between client and server
     * </pre>
     */
    public io.grpc.stub.StreamObserver<chat.Chat.ClientMessage> handleCommunication(
        io.grpc.stub.StreamObserver<chat.Chat.ServerMessage> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getHandleCommunicationMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ChatServer.
   */
  public static final class ChatServerBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ChatServerBlockingStub> {
    private ChatServerBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatServerBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ChatServerBlockingStub(channel, callOptions);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ChatServer.
   */
  public static final class ChatServerFutureStub
      extends io.grpc.stub.AbstractFutureStub<ChatServerFutureStub> {
    private ChatServerFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatServerFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ChatServerFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_HANDLE_COMMUNICATION = 0;

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
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_HANDLE_COMMUNICATION:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.handleCommunication(
              (io.grpc.stub.StreamObserver<chat.Chat.ServerMessage>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getHandleCommunicationMethod(),
          io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
            new MethodHandlers<
              chat.Chat.ClientMessage,
              chat.Chat.ServerMessage>(
                service, METHODID_HANDLE_COMMUNICATION)))
        .build();
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ChatServerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .addMethod(getHandleCommunicationMethod())
              .build();
        }
      }
    }
    return result;
  }
}
