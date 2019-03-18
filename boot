import time
from dht import DHT22
from machine import Pin
import dht
from umqttsimple import MQTTClient
import ubinascii
import machine
import micropython
import network
import esp
esp.osdebug(None)
import gc
gc.collect()

ssid = 'TNCAP1A2F1B'
password = '1D7DF9B8FC'
mqtt_server = '192.168.1.152'
client_id = ubinascii.hexlify(machine.unique_id())
topic_sub = b'output'
topic_pubt = b'temp'
topic_pubh = b'hum'

last_sensor_reading = 0
readings_interval = 5

station = network.WLAN(network.STA_IF)

station.active(True)
station.connect(ssid, password)

while station.isconnected() == False:
  pass

print('Connection successful')
print(station.ifconfig())

sensor = dht.DHT22(Pin(14))

led = machine.Pin(2, machine.Pin.OUT, value=0)
