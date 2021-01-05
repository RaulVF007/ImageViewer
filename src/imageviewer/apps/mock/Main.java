package imageviewer.apps.mock;

import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import imageviewer.view.ImageLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Image> images = new MockImageLoader().load(); 
        ImageDisplay imageDisplay = new MockImageDisplay();
        Scanner scanner = new Scanner(System.in);
        int index = 0;
        while(true){
            imageDisplay.display(images.get(index));
            String input = scanner.next().toUpperCase();
            if (input.equals("N")) index = bound(index+1, images.size());
            if (input.equals("P")) index = bound(index-1, images.size());
            if (input.equals("Q")) break;
        }
    }

    private static int bound(int index, int size) {
        if (index >= size) return 0;
        if (index < 0) return size -1;
        return index;
    }
    
    public static class MockImageDisplay implements ImageDisplay{

        @Override
        public void display(Image image) {
            System.out.println(image.getName());
        }
    
}
    
    public static class MockImageLoader implements ImageLoader{
        
        @Override
        public List <Image> load(){
            List<Image> list = new ArrayList<>();
            list.add(new Image("hola"));
            list.add(new Image("mundo"));
            list.add(new Image("bienvenido"));
            return list;
        }
    }
}
