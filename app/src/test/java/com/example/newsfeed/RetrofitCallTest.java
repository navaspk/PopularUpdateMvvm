package com.example.newsfeed;

import androidx.recyclerview.widget.SortedList;

import com.example.newsfeed.holdermodel.Response;
import com.example.newsfeed.utils.ApiCallInterfaceService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@Config(constants = BuildConfig.class, sdk = 21,
        manifest = "app/src/main/AndroidManifest.xml")
@RunWith(RobolectricGradleTestRunner.class)
public class RetrofitCallTest {

    private NewsFeedActivity mainActivity;

    @Mock
    private ApiCallInterfaceService mockRetrofitApiImpl;

    @Captor
    private ArgumentCaptor<SortedList.Callback<List<Response>>> callbackArgumentCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        ActivityController<NewsFeedActivity> controller =
                Robolectric.buildActivity(NewsFeedActivity.class);
        mainActivity = controller.get();

        controller.create();
    }

    @Test
    public void shouldFillAdapter() throws Exception {
        Mockito.verify(mockRetrofitApiImpl);

        int objectsQuantity = 10;
        List<Response> list = new ArrayList<Response>();
        for(int i = 0; i < objectsQuantity; ++i) {
            list.add(new Response());
        }

        callbackArgumentCaptor.getValue().compare(list, null);

        PopularNewsAdapter yourAdapter = mainActivity.getAdapter();
        // Simple test check if adapter has as many items as put into response
        assertThat(yourAdapter.getItemCount(), equalTo(objectsQuantity));
    }
}
