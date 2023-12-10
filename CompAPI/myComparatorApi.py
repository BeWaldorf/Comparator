import requests
from bs4 import BeautifulSoup

class ComparatorApi:
    URL: String = "https://www.amazon.com.be/s?k="
    page = requests.get(URL)
    soup = BeautifulSoup(page.content, "html.parser")

    search_results = soup.find_all(attrs={"data-component-type": "s-search-result"})

    for result in search_results:
        price_element = result.find("span", class_="a-price")
        if price_element:
            price = price_element.get_text()
            print(f"Price: {price}")

        title_element = result.find("h2", class_="a-size-mini")
        if title_element:
            title = title_element.get_text().strip()
            print(f"Title: {title}")

        shipping_element = result.find("span", class_="a-color-base")
        if shipping_element and "FREE Delivery" in shipping_element.get_text():
            shipping_price = shipping_element.get_text().strip()
            print(f"Shipping Price: {shipping_price}")

        print("----")
    