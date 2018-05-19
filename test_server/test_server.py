import socket

if __name__ == '__main__':
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect(("localhost", 45000))
    s.sendall("Hello world\0".encode('utf-8'))
    s.close()
