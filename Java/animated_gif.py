from PIL import Image

gif = Image.open('test1.gif')

isanimated = True

try:
    gif.seek(1)
    
except EOFError:
    isanimated = False
    
print isanimated    
