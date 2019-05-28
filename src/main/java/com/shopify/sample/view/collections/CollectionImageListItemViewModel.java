/*
 *   The MIT License (MIT)
 *
 *   Copyright (c) 2015 Shopify Inc.
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in
 *   all copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *   THE SOFTWARE.
 */

package com.shopify.sample.view.collections;

import android.support.annotation.NonNull;

import com.shopify.sample.R;
import com.shopify.sample.domain.model.Collection;
import com.shopify.sample.view.base.ListItemViewHolder;
import com.shopify.sample.view.base.ListItemViewModel;
import com.shopify.sample.view.widget.image.ShopifyDraweeView;

import butterknife.BindView;
import butterknife.OnClick;

final class CollectionImageListItemViewModel extends ListItemViewModel<Collection> {

  CollectionImageListItemViewModel(final Collection payload) {
    super(payload, R.layout.collection_image_list_item);
  }

  @Override public ListItemViewHolder<Collection, ListItemViewModel<Collection>> createViewHolder(
    final ListItemViewHolder.OnClickListener onClickListener) {
    return new ItemViewHolder(onClickListener);
  }

  @Override public boolean equalsById(@NonNull final ListItemViewModel other) {
    if (other instanceof CollectionImageListItemViewModel) {
      Collection otherPayload = ((CollectionImageListItemViewModel) other).payload();
      return payload().equalsById(otherPayload);
    }
    return false;
  }

  @Override public boolean equalsByContent(@NonNull final ListItemViewModel other) {
    if (other instanceof CollectionImageListItemViewModel) {
      Collection otherPayload = ((CollectionImageListItemViewModel) other).payload();
      return payload().equals(otherPayload);
    }
    return false;
  }

  static final class ItemViewHolder extends ListItemViewHolder<Collection, ListItemViewModel<Collection>> {
    @BindView(R.id.image) ShopifyDraweeView imageView;

    ItemViewHolder(@NonNull final ListItemViewHolder.OnClickListener onClickListener) {
      super(onClickListener);
    }

    @Override public void bindModel(@NonNull final ListItemViewModel<Collection> listViewItemModel, final int position) {
      super.bindModel(listViewItemModel, position);
      imageView.loadShopifyImage(listViewItemModel.payload().image);
    }

    @SuppressWarnings("unchecked") @OnClick(R.id.image)
    void onImageClick() {
      onClickListener().onClick(itemModel());
    }
  }
}
