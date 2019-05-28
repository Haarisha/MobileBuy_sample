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

package com.shopify.sample.domain.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.math.BigDecimal;
import java.util.List;

import static com.shopify.sample.util.Util.checkNotEmpty;
import static com.shopify.sample.util.Util.checkNotNull;

@SuppressWarnings("WeakerAccess")
public final class Checkout {
  @NonNull public final String id;
  @NonNull public final String webUrl;
  @NonNull public final String currency;
  public final boolean requiresShipping;
  @NonNull public final List<LineItem> lineItems;
  @NonNull public final ShippingRates shippingRates;
  @Nullable public final ShippingRate shippingLine;
  @NonNull public final BigDecimal taxPrice;
  @NonNull public final BigDecimal subtotalPrice;
  @NonNull public final BigDecimal totalPrice;

  public Checkout(@NonNull final String id, @NonNull final String webUrl, @NonNull final String currency, final boolean requiresShipping,
    @NonNull final List<LineItem> lineItems, @NonNull final ShippingRates shippingRates, @Nullable final ShippingRate shippingLine,
    @NonNull final BigDecimal taxPrice, @NonNull final BigDecimal subtotalPrice, @NonNull final BigDecimal totalPrice) {
    this.id = checkNotNull(id, "id == null");
    this.webUrl = checkNotNull(webUrl, "webUrl == null");
    this.currency = checkNotNull(currency, "currency == null");
    this.requiresShipping = requiresShipping;
    this.lineItems = checkNotEmpty(lineItems, "lineItems can't be empty");
    this.shippingRates = checkNotNull(shippingRates, "shippingRates == null");
    this.shippingLine = shippingLine;
    this.taxPrice = checkNotNull(taxPrice, "taxPrice == null");
    this.subtotalPrice = checkNotNull(subtotalPrice, "subtotalPrice == null");
    this.totalPrice = checkNotNull(totalPrice, "totalPrice == null");
  }

  @Override public String toString() {
    return "Checkout{" +
      "id='" + id + '\'' +
      ", webUrl='" + webUrl + '\'' +
      ", currency='" + currency + '\'' +
      ", requiresShipping=" + requiresShipping +
      ", lineItems=" + lineItems +
      ", shippingRates=" + shippingRates +
      ", shippingLine=" + shippingLine +
      ", taxPrice=" + taxPrice +
      ", subtotalPrice=" + subtotalPrice +
      ", totalPrice=" + totalPrice +
      '}';
  }

  @SuppressWarnings("WeakerAccess")
  public static final class LineItem {
    @NonNull public final String variantId;
    @NonNull public final String title;
    public final int quantity;
    @NonNull public final BigDecimal price;

    public LineItem(@NonNull final String variantId, @NonNull final String title, final int quantity, @NonNull final BigDecimal price) {
      this.variantId = checkNotNull(variantId, "variantId == null");
      this.title = checkNotNull(title, "title == null");
      this.quantity = quantity;
      this.price = checkNotNull(price, "price == null");
    }

    @Override public String toString() {
      return "LineItem{" +
        "variantId='" + variantId + '\'' +
        ", title='" + title + '\'' +
        ", quantity=" + quantity +
        ", price=" + price +
        '}';
    }
  }

  public static final class ShippingRates {
    public final boolean ready;
    @NonNull public final List<ShippingRate> shippingRates;

    public ShippingRates(final boolean ready, @NonNull final List<ShippingRate> shippingRates) {
      this.ready = ready;
      this.shippingRates = checkNotNull(shippingRates, "shippingRates == null");
    }

    @Override public String toString() {
      return "ShippingRates{" +
        "ready=" + ready +
        ", shippingRates=" + shippingRates +
        '}';
    }
  }

  public static final class ShippingRate {
    @NonNull public final String handle;
    @NonNull public final BigDecimal price;
    @NonNull public final String title;

    public ShippingRate(@NonNull final String handle, @NonNull final BigDecimal price, @NonNull final String title) {
      this.handle = checkNotNull(handle, "handle == null");
      this.price = checkNotNull(price, "price == null");
      this.title = checkNotNull(title, "title == null");
    }

    @Override public String toString() {
      return "ShippingRate{" +
        "handle='" + handle + '\'' +
        ", price=" + price +
        ", title='" + title + '\'' +
        '}';
    }

    @Override public boolean equals(final Object o) {
      if (this == o) return true;
      if (!(o instanceof ShippingRate)) return false;

      final ShippingRate that = (ShippingRate) o;

      if (!handle.equals(that.handle)) return false;
      if (!price.equals(that.price)) return false;
      return title.equals(that.title);

    }

    @Override public int hashCode() {
      int result = handle.hashCode();
      result = 31 * result + price.hashCode();
      result = 31 * result + title.hashCode();
      return result;
    }
  }
}
