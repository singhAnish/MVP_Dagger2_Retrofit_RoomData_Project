package sampleproject.android.com.TestProject.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import sampleproject.android.com.TestProject.R;
import sampleproject.android.com.TestProject.database.AppDatabase;
import sampleproject.android.com.TestProject.model.WonderActivityModelData;
import sampleproject.android.com.TestProject.ui.activity.wonder.WonderActivity;

public class WonderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<WonderActivityModelData> mModel;
    private RequestManager mManager;
    private WonderActivity mActivity;

    public WonderAdapter(WonderActivity activity, RequestManager manager, AppDatabase db) {
        this.mActivity = activity;
        this.mManager = manager;
        this.mModel  = db.wonderDao().getWonderData();
    }

    @Override @NonNull
    public WonderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mActivity).inflate(R.layout.list_place, parent, false);
        return new WonderAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder itemHolder, int position) {
        MyViewHolder holder = (MyViewHolder) itemHolder;
        final WonderActivityModelData model = mModel.get(position);
        holder.placeName.setText(model.getPlace());

        mManager.load(model.getUrl())
                .thumbnail(0.05f)//it will download 5% quality of image followed by original image(Progressive image for better user experience)
                .placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background)
                .fallback(R.drawable.ic_launcher_background)// In case when something goes wrong
                .diskCacheStrategy(DiskCacheStrategy.ALL)//It will cache the Image
                .crossFade().into(holder.placeImage);
    }

    @Override
    public int getItemCount() {
        return mModel.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView placeName;
        private final ImageView placeImage;
        private MyViewHolder(View view) {
            super(view);
            placeName = view.findViewById(R.id.placeName);
            placeImage = view.findViewById(R.id.placeImage);
        }
    }
}


