package jp.osaka.appppy.card.view;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import jp.osaka.appppy.card.R;

import static android.graphics.Color.WHITE;

/**
 * カード
 *
 * @author APPPPY
 */
public class CardFragment extends Fragment implements CustomAdapter.OnItemClickListener {

    /**
     * @serial リスナ
     */
    private OnCardFragmentInteractionListener mListener;

    /**
     * @serial リサイクルビュー
     */
    private RecyclerView mRecyclerView;

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @return A new instance of fragment BlankFragment.
     */
    public static CardFragment newInstance() {
        return new CardFragment();
    }

    /**
     * コンストラクタ
     */
    public CardFragment() {
        // Required empty public constructor
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card, container, false);
        mRecyclerView = view.findViewById(R.id.list_card);
        display();
        return view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (OnCardFragmentInteractionListener) getActivity();
        } catch (ClassCastException e) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                throw new ClassCastException(requireActivity().toString()
                        + " must implement OnFragmentInteractionListener");
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onItemClick(CustomAdapter adapter, int position, CustomItem item) {
        mListener.onCardFragmentInteraction(item.name);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated to
     * the activity and potentially other fragments contained in that activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnCardFragmentInteractionListener {
        void onCardFragmentInteraction(String name);
    }

    /**
     * ハニーコンボの有無
     *
     * @return  ハニーコンボ
     */
    public static boolean isHoneycomb() {
        return true;
    }

    /**
     * ハニーコンボタブレットの有無
     *
     * @return  ハニーコンボタブレット
     */
    public static boolean isHoneycombTablet(Context context) {
        return isHoneycomb() && (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                == Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }

    /**
     * 表示
     */
    private void display() {
        List<CustomItem> objects = new ArrayList<>();

        CustomItem data = new CustomItem();
        data.name = "YELLOW";
        data.color = Color.YELLOW;
        objects.add(data);

        data = new CustomItem();
        data.name = "RED";
        data.color = Color.RED;
        objects.add(data);

        data = new CustomItem();
        data.name = "ORANGE";
        data.color = R.color.orange_500;
        objects.add(data);

        data = new CustomItem();
        data.name = "GREEN";
        data.color = Color.GREEN;
        objects.add(data);

        data = new CustomItem();
        data.name = "ZEBRA";
        data.color = WHITE;
        objects.add(data);


        CustomAdapter customAdapater = new CustomAdapter(getActivity(), objects);

        // レイアウトの設定
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        RecyclerView.LayoutManager layoutManager = null;
        switch (config.orientation) {
            case Configuration.ORIENTATION_PORTRAIT:
            default:
                // 縦方向
                if (isHoneycombTablet(getActivity())) {
                    layoutManager = new GridLayoutManager(getActivity(), 2);
                } else {
                    layoutManager = new LinearLayoutManager(getActivity());
                }
                break;
            case Configuration.ORIENTATION_LANDSCAPE:
                // 縦方向
                if (isHoneycombTablet(getActivity())) {
                    layoutManager = new GridLayoutManager(getActivity(), 3);
                } else {
                    layoutManager = new GridLayoutManager(getActivity(), 2);
                }
                break;
            case Configuration.ORIENTATION_UNDEFINED:
                break;
            case Configuration.ORIENTATION_SQUARE:
                break;
        }
        mRecyclerView.setLayoutManager(layoutManager);

        // スクロールバーを表示しない
        mRecyclerView.setVerticalScrollBarEnabled(false);

        // リストビューのアイテムがクリックされた時に呼び出されるコールバックリスナーを登録します
        customAdapater.setOnItemClickListener(this);

        mRecyclerView.setAdapter(customAdapater);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }

}
