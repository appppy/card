
package jp.osaka.appppy.card.view;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import jp.osaka.appppy.card.R;

/**
 * カスタムアダプタ
 *
 * @author APPPPY
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> implements View.OnClickListener {
    /**
     * @serial コンテキスト
     */
    private final Context mContext;

    /**
     * @serial リスナ
     */
    private OnItemClickListener mListener;

    /**
     * @serial アイテム
     */
    private final List<CustomItem> mItems;

    /**
     * @serial Position
     */
    int mAnimatedPosition = ListView.INVALID_POSITION;

    /**
     * @serial リサイクルビュー
     */
    private RecyclerView mRecycler;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecycler= recyclerView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mRecycler = null;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (mRecycler == null) {
            return;
        }

        if (mListener != null) {
            int position = mRecycler.getChildAdapterPosition(v);
            CustomItem item = mItems.get(position);
            mListener.onItemClick(this, position, item);
        }
    }

    /**
     * ビューホルダー
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
        }
    }

    /**
     * コンストラクタ
     *
     * @param context　コンテキスト
     * @param data データリスト
     */
    public CustomAdapter(Context context, List<CustomItem> data) {
        mContext = context;
        mItems = data;
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.item_card, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CustomItem card = getItem(position);

        if (card != null) {
            ImageView image = holder.itemView.findViewById(R.id.imageView);
            if (card.color == Color.WHITE) {
                image.setImageResource(R.drawable.zebra);
                image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
            CardView cardView = holder.itemView.findViewById(R.id.card);
            switch(card.color) {
                case Color.YELLOW:
                    cardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.yellow));
                    break;
                case Color.RED:
                    cardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.red));
                    break;
                case Color.GREEN:
                    cardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.green));
                    break;
                case R.color.orange_500:
                    cardView.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.orange_500));
                    break;
                default:
                    break;
            }

        }

        if (mAnimatedPosition < position) {
            // XMLからアニメーターを作成
            Animator animator = AnimatorInflater.loadAnimator(mContext,
                    R.animator.card_slide_in);
            // アニメーションさせるビューをセット
            animator.setTarget(holder.view);
            // アニメーションを開始
            animator.start();
            mAnimatedPosition = position;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemCount() {
        return mItems.size();
    }

    /**
     * アイテムの取得
     * @author APPY
     * @param  position 位置
     * @return アイテム
     */
    private CustomItem getItem(int position) {
        return mItems.get(position);
    }

    /**
     * アイテムクリックリスナ
     */
    public interface OnItemClickListener {
        void onItemClick(CustomAdapter adapter, int position, CustomItem item);
    }

    /**
     * アイテムクリックリスナ設定
     *
     * @param listener リスナ
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
}
