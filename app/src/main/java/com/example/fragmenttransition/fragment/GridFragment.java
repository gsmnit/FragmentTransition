package com.example.fragmenttransition.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.core.app.SharedElementCallback;
import android.transition.TransitionInflater;
import android.view.View.OnLayoutChangeListener;
import com.example.fragmenttransition.R;
import com.example.fragmenttransition.adapter.GridAdapter;
import com.example.fragmenttransition.MainActivity;

import java.util.List;
import java.util.Map;

/**
 * A fragment for displaying a grid of images.
 */
public class GridFragment extends Fragment {

    private RecyclerView recyclerView;
    private final String LOGTAG = "GridFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.i(LOGTAG,"onCreateView");
        recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_grid, container, false);
        recyclerView.setAdapter(new GridAdapter(this));


        prepareTransitions();
        postponeEnterTransition();

        return recyclerView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        scrollToPosition();
    }

    /**
     * Scrolls the recycler view to show the last viewed item in the grid. This is important when
     * navigating back from the grid.
     */
    private void scrollToPosition() {
        Log.i(LOGTAG,"on scrollToPosition");
        recyclerView.addOnLayoutChangeListener(new OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v,
                                       int left,
                                       int top,
                                       int right,
                                       int bottom,
                                       int oldLeft,
                                       int oldTop,
                                       int oldRight,
                                       int oldBottom) {
                recyclerView.removeOnLayoutChangeListener(this);
                final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                View viewAtPosition = layoutManager.findViewByPosition(MainActivity.getCurrentPosition());
                // Scroll to position if the view for the current position is null (not currently part of
                // layout manager children), or it's not completely visible.
                if (viewAtPosition == null || layoutManager
                        .isViewPartiallyVisible(viewAtPosition, false, true)) {
                    Log.i(LOGTAG,"on scrollToPosition inside if");
                    recyclerView.post(() -> layoutManager.scrollToPosition(MainActivity.getCurrentPosition()));
                }
            }
        });
    }

    /**
     * Prepares the shared element transition to the pager fragment, as well as the other transitions
     * that affect the flow.
     */
    private void prepareTransitions(){
        Log.i(LOGTAG,"on prINT LOG");

        setExitTransition(TransitionInflater.from(getContext())
                .inflateTransition(R.transition.grid_exit_transition));

        setExitSharedElementCallback(
                new SharedElementCallback() {
                    @Override
                    public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                        Log.i("LOGTAG","onMapSharedElements");
                        // Locate the ViewHolder for the clicked position.
                        RecyclerView.ViewHolder selectedViewHolder = recyclerView
                                .findViewHolderForAdapterPosition(MainActivity.getCurrentPosition());
                        if (selectedViewHolder == null) {
                            Log.i("LOGTAG","onMapSharedElements");
                            return;
                        }

                        Log.i("LOGTAG","onMapSharedElements");
//                         Map the first shared element name to the child ImageView.
                        sharedElements
                                .put(names.get(0), selectedViewHolder.itemView.findViewById(R.id.card_image));
                    }
                });
    }
}
