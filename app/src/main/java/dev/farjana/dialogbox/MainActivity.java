package dev.farjana.dialogbox;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    int vidW, vidH;
    Uri uri;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.convert;

         uri = Uri.parse(videoPath);


            final Dialog dialog = new Dialog(MainActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.xmldesign);


            WindowManager.LayoutParams lp = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
            lp.copyFrom(dialog.getWindow().getAttributes());
            dialog.getWindow().setAttributes(lp);
            getWindow().setFormat(PixelFormat.TRANSLUCENT);


            ColorDrawable back = new ColorDrawable(Color.WHITE);
            InsetDrawable inset = new InsetDrawable(back, 10,100,10,0);
            dialog.getWindow().setBackgroundDrawable(inset);
    /*
            TextView text = dialog.findViewById(R.id.text);*/


            Display display = getWindowManager().getDefaultDisplay();
            int displayWidth = display.getWidth(); // ((display.getWidth()*20)/100)
            int displayHeight = display.getHeight();// ((display.getHeight()*30)/100)


            VideoView videoView = dialog.findViewById(R.id.myvideoview);
            ImageView myImageView = dialog.findViewById(R.id.myImageView);

            // Video Contents .........


            videoView.setVideoURI(uri);

            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(dialog.getContext(), uri);
            vidW = Integer.parseInt(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH));
            vidH = Integer.parseInt(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT));
            retriever.release();

            MediaController media = new MediaController(dialog.getContext());

            videoView.setMediaController(media);
            media.setAnchorView(videoView);

            if(vidW > vidH){
                if (vidW>displayWidth){
                    vidW = displayWidth*3/4;
                    vidH = displayHeight/4;
                }else{
                    if(vidH> displayHeight){

                        vidH = displayHeight/4;
                    }

                }
            }else{
                if (vidH>displayHeight){
                    vidH = displayHeight*3/4;
                    vidW = displayWidth/3;
                }else{
                    if (vidW>displayWidth){
                        vidW = displayWidth/3;
                        vidH = displayHeight*3/4;
                    }

                }
            }




            videoView.getLayoutParams().height = vidH;
            videoView.getLayoutParams().width = vidW;
            videoView.requestLayout();

            myImageView.getLayoutParams().height=vidH;
            myImageView.getLayoutParams().width= vidW;
            myImageView.requestLayout();


            videoView.start();
            dialog.setCancelable(false);
            dialog.show();








        /*if (vidW > vidH) {
            if (vidW > displayWidth * 3 / 4) {

                vidWidth = displayWidth * 3 / 4;
                vidHeight = displayHeight / 4;
                u = 20;
                v = 24;
            } else {

                vidWidth = displayWidth / 3;
                vidHeight = displayHeight / 4;
                u = 28;
                v = 34;
            }

        } else {
            if (vidW > displayWidth * 3 / 4) {
                vidWidth = displayWidth * 3 / 5;
                vidHeight = displayHeight * 2 / 5;
                u = 38;
                v = 50;
            } else {

                vidWidth = vidW;
                vidHeight = displayHeight * 3 / 5;
                u = 30;
                v = 48;
            }
        }*/

/*
        int h = vidHeight;
        int w = vidWidth;
        videoLayout.getLayoutParams().height = h;
        videoLayout.getLayoutParams().width = w;
        videoLayout.requestLayout();*/
    }
}